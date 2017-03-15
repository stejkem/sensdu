package com.sensdu.mediawikiapi;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author Stejkem
 */
@Service
public class MediaWikiAPIRequestBuilder {

    private final String APIEndpoint;

    public MediaWikiAPIRequestBuilder(String baseLanguage) {
        APIEndpoint = String.format("https://%1$s.wikipedia.org/w/api.php", baseLanguage);
    }

    public URI buildTranslationRequestURI(String requestedWord) {
        URI requestURI = UriComponentsBuilder.fromUriString(APIEndpoint)
                .queryParam("action", "query")
                .queryParam("titles", requestedWord)
                .queryParam("redirects")
                .queryParam("prop", "langlinks")
                .queryParam("formatversion", "2")
                .queryParam("lllimit", "500")
                .queryParam("llprop", "url")
                .queryParam("format", "json")
                .build().toUri();

        return requestURI;
    }

}
