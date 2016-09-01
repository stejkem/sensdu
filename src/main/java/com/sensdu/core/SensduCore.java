package com.sensdu.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


public class SensduCore {
    private String state;

    private final String sourceWord;
    private String sourceWordWikiUrl;

    private final String fromLanguage;
    private final String toLanguage;

    private String translatedWord;
    private String translatedWordWikiUrl;


    private List<String> sourceWordSearchSuggestion;
    private Map<String, WordAndURLTuple> langsAndWords;

    private Boolean isDisambiguationArticle;
    private List<String> searchSuggestion;

    private String userAgent;

    @JsonCreator
    public SensduCore(@JsonProperty("sourceWord") String sourceWord,
                      @JsonProperty("fromLanguage") String fromLanguage,
                      @JsonProperty("toLanguage") String toLanguage) throws Exception {

        this.sourceWord = sourceWord.substring(0, 1).toUpperCase() + sourceWord.toLowerCase().substring(1);
        this.fromLanguage = fromLanguage.toLowerCase();
        this.toLanguage = toLanguage.toLowerCase();
        fillInObject();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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

    public String getSourceWordWikiUrl() throws Exception {
        return sourceWordWikiUrl;
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

    public String getTranslatedWordWikiUrl() throws Exception{
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

        isDisambiguationArticle = answerFromWebSite.toString().contains("\"disambiguation\":");

        Object jsonDocument = Configuration.defaultConfiguration().jsonProvider().parse(answerFromWebSite.toString());
        List<String> langsKey = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].lang");
        List<String> wordValues = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].title");
        List<String> urlValues = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].url");
        List<String> wordURLs = JsonPath.read(jsonDocument, "$.query.pages[*].fullurl");
        sourceWordWikiUrl = wordURLs.get(0);

        // New block on links
        searchSuggestion = new ArrayList<>();
        Document doc = Jsoup.connect(sourceWordWikiUrl).get();
        Elements links = doc.select("li > a[href], i > a[href]");
        for (Element currentLink : links) {
            if(!currentLink.attr("title").equals("") &&
                    !currentLink.attr("title").contains("[z]") &&
                    currentLink.attr("href").contains("/wiki/") &&
                    !currentLink.attr("href").contains(":") &&
                    !currentLink.attr("class").equals("new") &&
                    !currentLink.attr("class").equals("image") &&
                    !currentLink.attr("class").equals("external text")
                    ) {
                searchSuggestion.add(currentLink.attr("title"));
            }
        }
        // End of block

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
        request.append("&prop=info|links|langlinks|pageprops&formatversion=2&lllimit=500&pllimit=500&llprop=url&inprop=url&format=json");
        return new URL(request.toString());
    }

}
