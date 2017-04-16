package com.sensdu.service;

import java.util.LinkedHashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

        LinkedHashMap langAndLink = (LinkedHashMap) (((JSONArray) JsonPath.parse(APIResponse).read(langAndLinksPath)).get(0));
        String translation = langAndLink.get("title").toString();
        String translationUrl = langAndLink.get("url").toString();

        String wordURL = ((JSONArray) JsonPath.parse(APIResponse).read(wordURLPath)).get(0).toString();

        TranslationResponse translationResponse = new TranslationResponse();
        translationResponse.setFromLanguage(translationQuery.getFromLanguage());
        translationResponse.setToLanguage(translationQuery.getToLanguage());
        translationResponse.setWord(translationQuery.getWord());
        translationResponse.setWordURL(wordURL);
        translationResponse.setWordWikiExtract(getExtract(wordURL));
        translationResponse.setTranslation(translation);
        translationResponse.setTranslationURL(translationUrl);
        translationResponse.setTranslationWikiExtract(getExtract(translationUrl));

        return translationResponse;
    }

    private String getExtract(String url) {
        int cropPoint = 120;

        Document document = JsoupRequestWrapper.tryToRetrieveDocument(url);
        for (Element element : document.select("#mw-content-text > p")) {
            String paragraph = element.text();

            if (paragraph.length() >= cropPoint) {
                String extract = paragraph.substring(0, paragraph.indexOf(" ", cropPoint)) + "...";

                return replaceAllIndexPointers(extract);
            }
        }

        return "...";
    }

    private String replaceAllIndexPointers(String string) {
        return string.replaceAll("\\[(.*?)\\]", "");
    }
}
