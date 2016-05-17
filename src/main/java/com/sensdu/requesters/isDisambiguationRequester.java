package com.sensdu.requesters;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class isDisambiguationRequester implements Requester {

    private String wordForCheck;
    private String languageOfWord;

    public isDisambiguationRequester(String wordForCheck, String languageOfWord) {
        this.wordForCheck = wordForCheck;
        this.languageOfWord = languageOfWord;
    }

    public Boolean isDisambiguationArticle() throws Exception {
        URL requestToWiki = URLBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(requestToWiki.openStream()));
        StringBuilder answerFromWebSite = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            answerFromWebSite.append(inputLine);
        }
        return answerFromWebSite.toString().contains("disambiguation");
    }

    private URL URLBuilder() throws Exception {
        StringBuilder request = new StringBuilder();
        request.append("https://");
        request.append(languageOfWord);
        request.append(".wikipedia.org/w/api.php?action=query&prop=pageprops&ppprop=disambiguation&redirects&titles=");
        request.append(wordForCheck.replaceAll("\\s+", "%20"));
        request.append("&format=json");
        return new URL(request.toString());
    }
}
