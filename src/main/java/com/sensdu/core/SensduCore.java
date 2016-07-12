package com.sensdu.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


public class SensduCore {
    private String state;

    private final String sourceWord;
    private final String fromLanguage;
    private final String toLanguage;

    private String sourceWordlURL;
    private List<String> sourceWordSearchSuggestion;
    private String translatedWord;
    private String translatedWordURL;


    private Map<String, WordAndURLTuple> langsAndWords;
    private String word;
    private String wordURL;
    private Boolean isDisambiguationArticle;
    private List<String> searchSuggestion;

    @JsonCreator
    public SensduCore(@JsonProperty("sourceWord") String sourceWord,
                      @JsonProperty("fromLanguage") String fromLanguage,
                      @JsonProperty("toLanguage") String toLanguage) throws Exception {

        this.sourceWord = sourceWord.substring(0, 1).toUpperCase() + sourceWord.toLowerCase().substring(1);
        this.fromLanguage = fromLanguage.toLowerCase();
        this.toLanguage = toLanguage.toLowerCase();
        fillInObject();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public String getSourceWordlURL() throws Exception {
        return  wordURL;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public String getTranslatedWord() throws Exception{
        return langsAndWords.get(toLanguage).getWord();
    }

    public String getTranslatedWordURL() throws Exception{
        return langsAndWords.get(toLanguage).getURL();
    }

    public List<String> getSourceWordSearchSuggestion() throws Exception {
        return searchSuggestion;
    }

    public Boolean isDisambiguationArticle() throws Exception {
            return isDisambiguationArticle;
    }

    private void fillInObject() throws Exception {
        URL requestToWiki = langLinksRequestBuilder();

        BufferedReader in = new BufferedReader(new InputStreamReader(requestToWiki.openStream()));
        StringBuilder answerFromWebSite = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            answerFromWebSite.append(inputLine);
        }

        isDisambiguationArticle = answerFromWebSite.toString().contains("disambiguation");

        Object jsonDocument = Configuration.defaultConfiguration().jsonProvider().parse(answerFromWebSite.toString());
        List<String> langsKey = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].lang");
        List<String> wordValues = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].title");
        List<String> urlValues = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].url");
        List<String> wordURLs = JsonPath.read(jsonDocument, "$.query.pages[*].fullurl");
        List<String> wordSearchSuggestion = JsonPath.read(jsonDocument, "$.query.pages[*].links[*].title");
        searchSuggestion = wordSearchSuggestion.subList(1, wordSearchSuggestion.size());
        wordURL = wordURLs.get(0);

        Iterator<String> langsKeyIter = langsKey.iterator();
        Iterator<String> wordValuesIter = wordValues.iterator();
        Iterator<String> wordURLsIter = urlValues.iterator();
        langsAndWords = new HashMap<>();
        while (langsKeyIter.hasNext() && wordValuesIter.hasNext() && wordURLsIter.hasNext()) {
            langsAndWords.put(langsKeyIter.next(), new WordAndURLTuple(wordValuesIter.next(), wordURLsIter.next()));
        }

    }

    private URL langLinksRequestBuilder() throws Exception {
        StringBuilder request = new StringBuilder();
        request.append("https://");
        request.append(fromLanguage);
        request.append(".wikipedia.org/w/api.php?action=query&titles=");
        request.append(sourceWord.replaceAll("\\s+","%20"));
        request.append("&redirects"); //added to check not only direct queries but also redirects
        request.append("&prop=info|links|langlinks|pageprops&formatversion=2&lllimit=500&llprop=url&inprop=url&format=json");
        return new URL(request.toString());
    }
}
