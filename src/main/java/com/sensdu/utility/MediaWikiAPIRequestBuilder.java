package com.sensdu.utility;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Stejkem
 */
public class MediaWikiAPIRequestBuilder {

    private MediaWikiAPIRequestBuilder() {
    }

    public static String buildTranslationRequestURI(String requestedWord, String baseLanguage) {
        String APIEndpoint = String.format("https://%1$s.wikipedia.org/w/api.php", baseLanguage);

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
