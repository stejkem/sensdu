package com.sensdu.domain;

/**
 * @author Stejkem
 */
public class TranslationResponse {

    private String word;

    private String fromLanguage;

    private String toLanguage;

    private String translation;

    private String wordURL;

    private String translationURL;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getWordURL() {
        return wordURL;
    }

    public void setWordURL(String wordURL) {
        this.wordURL = wordURL;
    }

    public String getTranslationURL() {
        return translationURL;
    }

    public void setTranslationURL(String translationURL) {
        this.translationURL = translationURL;
    }
}
