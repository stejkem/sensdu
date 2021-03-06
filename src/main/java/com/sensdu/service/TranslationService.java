package com.sensdu.service;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONArray;

import com.jayway.jsonpath.JsonPath;

import com.sensdu.domain.TranslationQuery;
import com.sensdu.domain.TranslationResponse;
import com.sensdu.utility.JsoupRequestWrapper;
import com.sensdu.utility.MediaWikiAPIRequestBuilder;

/**
 * @author Stejkem
 */
@Service
public class TranslationService {

    private final Logger logger = LoggerFactory.getLogger(TranslationService.class);

    private TranslationQuery translationQuery;

    public TranslationQuery getTranslationQuery() {
        return translationQuery;
    }

    public void setTranslationQuery(TranslationQuery translationQuery) {
        this.translationQuery = translationQuery;
    }

    public TranslationResponse retrieveTranslationResponse() {
        String langAndLinksPath = String.format(".query.pages[0].langlinks[?(@.lang == '%1$s')]", translationQuery.getToLanguage());
        String wordURLPath = ".query.pages[0].fullurl";

        String translationURI = MediaWikiAPIRequestBuilder.buildTranslationRequestURI(translationQuery.getWord(), translationQuery.getFromLanguage());
        String APIResponse = JsoupRequestWrapper.tryToRetrieveJson(translationURI);

        String wordURL = ((JSONArray) JsonPath.parse(APIResponse).read(wordURLPath)).get(0).toString();
        String wordExtract = getExtract(wordURL);

        String translation = "no-translation-available";
        String translationUrl = "";
        String translationExtract = "";

        if (!((JSONArray) JsonPath.parse(APIResponse).read(langAndLinksPath)).isEmpty()) {
            LinkedHashMap langAndLink = (LinkedHashMap) (((JSONArray) JsonPath.parse(APIResponse).read(langAndLinksPath)).get(0));
            translation = langAndLink.get("title").toString();
            translationUrl = langAndLink.get("url").toString();
            translationExtract = getExtract(translationUrl);
        }

        TranslationResponse translationResponse = new TranslationResponse();
        translationResponse.setFromLanguage(translationQuery.getFromLanguage());
        translationResponse.setToLanguage(translationQuery.getToLanguage());
        translationResponse.setWord(translationQuery.getWord());
        translationResponse.setWordURL(wordURL);
        translationResponse.setWordWikiExtract(wordExtract);
        translationResponse.setTranslation(translation);
        translationResponse.setTranslationURL(translationUrl);
        translationResponse.setTranslationWikiExtract(translationExtract);

        return translationResponse;
    }

    private String getExtract(String url) {
        int cropPoint = 140;
        final int minLength = 30;

        Document document = JsoupRequestWrapper.tryToRetrieveDocument(url);
        Elements elements = document.select("#mw-content-text > p");
        Optional<Element> optionalElement = elements.stream().filter(element -> element.text().length() > minLength).findFirst();

        if (optionalElement.isPresent()) {
            String paragraph = optionalElement.get().text();
            int nextSpaceIndex = paragraph.indexOf(" ", cropPoint);

            String extract;
            if (paragraph.length() > cropPoint && nextSpaceIndex != -1) {
                extract = paragraph.substring(0, nextSpaceIndex);
            } else {
                extract = paragraph;
            }

            for (int i = extract.length() - 1; i > 0; i--) {
                if (Character.isLetter(extract.charAt(i))) {
                    break;
                } else {
                    extract = StringUtils.chop(extract);
                }
            }

            extract += "...";

            return replaceAllIndexPointers(extract);
        } else {
            return "...";
        }
    }

    private String replaceAllIndexPointers(String string) {
        return string.replaceAll("\\[(.*?)\\]", "");
    }
}