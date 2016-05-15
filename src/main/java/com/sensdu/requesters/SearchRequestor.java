package com.sensdu.requesters;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class SearchRequestor implements Requester {

    private String wordForSearch;
    private String languageOfWord;

    public SearchRequestor(String wordForSearch, String languageOfWord) {
        this.wordForSearch = wordForSearch;
        this.languageOfWord = languageOfWord;
    }

    public List<String> getSearchSuggestion() throws Exception {
        URL requestToWiki = URLBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(requestToWiki.openStream()));
        StringBuilder answerFromWebSite = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            answerFromWebSite.append(inputLine);
        }

        Object jsonDocument = Configuration.defaultConfiguration().jsonProvider().parse(answerFromWebSite.toString());
        List<String> wordSearchSuggestion = JsonPath.read(jsonDocument, "$.query.search[*].title");

        return wordSearchSuggestion.subList(1, wordSearchSuggestion.size());
    }

    private URL URLBuilder() throws Exception {
        StringBuilder request = new StringBuilder();
        request.append("https://");
        request.append(languageOfWord);
        request.append(".wikipedia.org/w/api.php?action=query&list=search&srsearch=");
        request.append(wordForSearch.replaceAll("\\s+", "%20"));
        request.append("&srlimit=500&format=json");
        return new URL(request.toString());
    }

}

