package com.sensdu.core;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;
import com.sensdu.domain.TranslationQuery;
import com.sensdu.domain.TranslationResponse;
import com.sensdu.service.JsoupRequestWrapper;
import com.sensdu.service.MediaWikiAPIRequestBuilder;
import net.minidev.json.JSONArray;

/**
 * @author Stejkem
 */
public class Sensdu {

    private final Logger logger = LoggerFactory.getLogger(Sensdu.class);

    private TranslationQuery translationQuery;

    private MediaWikiAPIRequestBuilder APIBuilder;

    public Sensdu(TranslationQuery translationQuery) throws Exception {
        this.translationQuery = translationQuery;
        this.APIBuilder = new MediaWikiAPIRequestBuilder(translationQuery.getFromLanguage());
    }

    public TranslationResponse retrieveTranslationResponse() throws IOException {
        String langAndLinksPath = String.format(".query.pages[0].langlinks[?(@.lang == '%1$s')]", translationQuery.getToLanguage());
        String wordURLPath = ".query.pages[0].fullurl";

        String translationURI = APIBuilder.buildTranslationRequestURI(translationQuery.getWord());
        String APIResponse = JsoupRequestWrapper.retrieveJson(translationURI);
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
}
