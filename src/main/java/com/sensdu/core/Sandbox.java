package com.sensdu.core;

import com.sensdu.requesters.SearchRequestor;

public class Sandbox {
    public static void main(String[] args) throws Exception {
        SearchRequestor core = new SearchRequestor("індекс", "uk");
        for (String suggestion: core.getSearchSuggestion()) {
            System.out.println(suggestion);
        }

    }
}
