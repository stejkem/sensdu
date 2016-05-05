package com.sensdu.requesters;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LangLinksRequester implements Requester {

    private Map<String, WordAndURLTuple> langsAndWords;
    private String word;
    private String wordURL;
    private String toLanguage;
    private String fromLanguage;

    public LangLinksRequester(String word, String fromLanguage, String toLanguage) throws Exception {
        langsAndWords = new HashMap<>();
        this.word = word;
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        fillInLangsAndWordMap();
        fillInWordURL();
    }

    public String getWordTranslation() {
        return langsAndWords.get(toLanguage).getWord();
    }

    public String getURLOfTranlatedWord() {
        return langsAndWords.get(toLanguage).getURL();
    }

    public String getWordURL() {return  wordURL; }

    private void fillInLangsAndWordMap() throws Exception {
        URL requestToWiki = langLinksRequestBuilder();

        BufferedReader in = new BufferedReader(new InputStreamReader(requestToWiki.openStream()));
        StringBuilder answerFromWebSite = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            answerFromWebSite.append(inputLine);
        }

        Object jsonDocument = Configuration.defaultConfiguration().jsonProvider().parse(answerFromWebSite.toString());
        List<String> langsKey = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].lang");
        List<String> wordValues = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].title");
        List<String> urlValues = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].url");

        Iterator<String> langsKeyIter = langsKey.iterator();
        Iterator<String> wordValuesIter = wordValues.iterator();
        Iterator<String> wordURLsIter = urlValues.iterator();

        while (langsKeyIter.hasNext() && wordValuesIter.hasNext() && wordURLsIter.hasNext()) {
            langsAndWords.put(langsKeyIter.next(), new WordAndURLTuple(wordValuesIter.next(), wordURLsIter.next()));
        }

    }

    private void fillInWordURL() throws Exception{
        URL requestToWiki = pageInfoRequestBuilder();

        BufferedReader in = new BufferedReader(new InputStreamReader(requestToWiki.openStream()));
        StringBuilder answerFromWebSite = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            answerFromWebSite.append(inputLine);
        }

        Object jsonDocument = Configuration.defaultConfiguration().jsonProvider().parse(answerFromWebSite.toString());
        List<String> wordURLs = JsonPath.read(jsonDocument, "$.query.pages[*].fullurl");
        wordURL = wordURLs.get(0);
    }

    private URL langLinksRequestBuilder() throws Exception {
        StringBuilder request = new StringBuilder();
        request.append("https://");
        request.append(fromLanguage);
        request.append(".wikipedia.org/w/api.php?action=query&titles=");
        request.append(word.replaceAll("\\s+","%20"));
        request.append("&redirects"); //added to check not only direct queries but also redirects
        request.append("&prop=langlinks&formatversion=2&lllimit=500&format=json&llprop=url");
        return new URL(request.toString());
    }

    private URL pageInfoRequestBuilder() throws Exception {
        StringBuilder request = new StringBuilder();
        request.append("https://");
        request.append(fromLanguage);
        request.append(".wikipedia.org/w/api.php?action=query&titles=");
        request.append(word.replaceAll("\\s+","%20"));
        request.append("&prop=info&inprop=url&format=json");
        return new URL(request.toString());
    }

}
