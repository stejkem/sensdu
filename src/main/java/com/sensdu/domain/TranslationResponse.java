package com.sensdu.domain;

/**
 * @author Stejkem
 */
public class TranslationResponse {

    private String fromLanguage;

    private String toLanguage;

    private String word;

    private String wordURL;

    private String wordWikiExtract;

    private String translation;

    private String translationURL;

    private String translationWikiExtract;

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

    public String getWordWikiExtract() {
      return wordWikiExtract;
    }

    public void setWordWikiExtract(String wordWikiExtract) {
      this.wordWikiExtract = wordWikiExtract;
    }

    public String getTranslationWikiExtract() {
      return translationWikiExtract;
    }

    public void setTranslationWikiExtract(String translationWikiExtract) {
      this.translationWikiExtract = translationWikiExtract;
    }
}
