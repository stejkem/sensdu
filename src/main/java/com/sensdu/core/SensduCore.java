package com.sensdu.core;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.sensdu.requesters.LangLinksRequester;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SensduCore {

    private String sourceWord;
    private List<String> sourceWordSearchSuggestion;
    private String vectorOfTranslation;
    private String translation;
    private String translatedWordURL;

    public SensduCore() { }

    public SensduCore(String sourceWord, String vectorOfTranslation) {
        this.sourceWord = sourceWord;
        this.vectorOfTranslation = vectorOfTranslation;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setSourceWord(String sourceWord) {
        sourceWord = sourceWord.toLowerCase();
        sourceWord = sourceWord.substring(0, 1).toUpperCase() + sourceWord.substring(1);
        this.sourceWord = sourceWord;
    }

    public String getVectorOfTranslation() {
        return vectorOfTranslation;
    }

    public void setVectorOfTranslation(String vectorOfTranslation) {
        this.vectorOfTranslation = vectorOfTranslation;
    }

    public String getTranslation() throws Exception{
        return new LangLinksRequester(sourceWord, vectorOfTranslation).getWordTranslation();
    }

    public String getTranslatedWordURL() throws Exception{
        return new LangLinksRequester(sourceWord, vectorOfTranslation).getURLOfTranlatedWord();
    }

}
