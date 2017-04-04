package com.sensdu.service;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.sensdu.utility.JsoupRequestWrapper;
import com.sensdu.utility.MediaWikiAPIRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;
import com.sensdu.domain.TranslationQuery;
import com.sensdu.domain.TranslationResponse;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Service;

/**
 * @author Stejkem
 */
@Service
public class TranslationService {

    private final Logger logger = LoggerFactory.getLogger(TranslationService.class);

    private TranslationQuery translationQuery;

    public TranslationResponse retrieveTranslationResponse() {
        String langAndLinksPath = String.format(".query.pages[0].langlinks[?(@.lang == '%1$s')]", translationQuery.getToLanguage());
        String wordURLPath = ".query.pages[0].fullurl";

        String translationURI = MediaWikiAPIRequestBuilder.buildTranslationRequestURI(translationQuery.getWord(), translationQuery.getFromLanguage());
        String APIResponse = tryToMakeWebRequest(translationURI);
        LinkedHashMap langAndLink = (LinkedHashMap) (((JSONArray) JsonPath.parse(APIResponse).read(langAndLinksPath)).get(0));
        String wordURL = ((JSONArray) JsonPath.parse(APIResponse).read(wordURLPath)).get(0).toString();

        TranslationResponse translationResponse = new TranslationResponse();
        translationResponse.setWord(translationQuery.getWord());
        translationResponse.setFromLanguage(translationQuery.getFromLanguage());
        translationResponse.setToLanguage(translationQuery.getToLanguage());
        translationResponse.setTranslation((String) langAndLink.get("title"));
        translationResponse.setTranslationURL((String) langAndLink.get("url"));
        translationResponse.setWordURL(wordURL);

        return translationResponse;
    }

    private String tryToMakeWebRequest(String translationURI) {
        try {
            return JsoupRequestWrapper.retrieveJson(translationURI);
        } catch (IOException exception) {
            String message = "Looks like the base language of the page was set incorrectly - " + exception.getMessage();
            logger.error(message);

            throw new RuntimeException(message);
        }
    }

    public void setTranslationQuery(TranslationQuery translationQuery) {
        this.translationQuery = translationQuery;
    }

    public TranslationQuery getTranslationQuery() {
        return translationQuery;
    }
}
