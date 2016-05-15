package com.sensdu.core;

import com.sensdu.requesters.LangLinksRequester;
import com.sensdu.requesters.SearchRequestor;

import java.util.List;

public class SensduCore {

    private String fromLanguage;
    private String toLanguage;
    private String sourceWord;
    private String sourceWordlURL;
    private List<String> sourceWordSearchSuggestion;
    private String translatedWord;
    private String translatedWordURL;

    public SensduCore() { }

    public SensduCore(String sourceWord, String fromLanguage, String toLanguage) {
        this.sourceWord = sourceWord.toLowerCase();
        this.fromLanguage = fromLanguage.toLowerCase();
        this.toLanguage = toLanguage.toLowerCase();
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setSourceWord(String sourceWord) {
        this.sourceWord =  sourceWord.substring(0, 1).toUpperCase() + sourceWord.toLowerCase().substring(1);

    }

    public String getSourceWordlURL() throws Exception {
        return new LangLinksRequester(sourceWord, fromLanguage, toLanguage).getWordURL();
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage.toLowerCase();
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage.toLowerCase();
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public String getTranslatedWord() throws Exception{
        return new LangLinksRequester(sourceWord, fromLanguage, toLanguage).getWordTranslation();
    }

    public String getTranslatedWordURL() throws Exception{
        return new LangLinksRequester(sourceWord, fromLanguage, toLanguage).getURLOfTranlatedWord();
    }

    public List<String> getSourceWordSearchSuggestion() throws Exception {
        return new SearchRequestor(sourceWord, fromLanguage).getSearchSuggestion();
    }
}
