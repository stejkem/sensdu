package com.sensdu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Stejkem
 */
@Service
public class MediaWikiAPIRequestBuilder {

    private final String APIEndpoint;

    public MediaWikiAPIRequestBuilder(String baseLanguage) {
        APIEndpoint = String.format("https://%1$s.wikipedia.org/w/api.php", baseLanguage);
    }

    public String buildTranslationRequestURI(String requestedWord) {
        String requestURI = UriComponentsBuilder.fromUriString(APIEndpoint)
                .queryParam("action", "query")
                .queryParam("titles", requestedWord)
                .queryParam("redirects")
                .queryParam("prop", "langlinks|info")
                .queryParam("formatversion", "2")
                .queryParam("lllimit", "500")
                .queryParam("llprop", "url")
                .queryParam("inprop", "url")
                .queryParam("format", "json")
                .build(false).toUriString();

        return requestURI;
    }
}
