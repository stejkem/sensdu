package com.sensdu.core;

public class WordAndURLTuple {

    private String word;
    private String URL;

    public WordAndURLTuple(String word, String URL) {
        this.word = word;
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public String getWord() {
        return word;
    }

}
