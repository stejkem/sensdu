package com.sensdu.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * According to the User-Agent policy -
 * <a> https://meta.wikimedia.org/wiki/User-Agent_policy </a>
 * Wikimedia sites require a HTTP User-Agent header for all requests.
 *
 * @author Stejkem
 */
public class JsoupRequestWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsoupRequestWrapper.class);

    private static final String USER_AGENT = "Sensdu/1.0";

    private static final String REFERRER = "http://www.sensdu.com";

    private JsoupRequestWrapper() {
    }

    public static Document tryToRetrieveDocument(String url) {
        try {
            String decodedURL = URLDecoder.decode(url, StandardCharsets.UTF_8.toString());

            return retrieveDocument(decodedURL);
        } catch (IOException exception) {
            String message = "Can not parse " + url + " - " + exception.getMessage();
            LOGGER.error(message);

            throw new RuntimeException(message);
        }
    }

    public static String tryToRetrieveJson(String url) {
        try {
            String decodedURL = URLDecoder.decode(url, StandardCharsets.UTF_8.toString());

            return retrieveJson(decodedURL);
        } catch (IOException exception) {
            String message = "Can not connect to " + url + " - " + exception.getMessage();
            LOGGER.error(message);

            throw new RuntimeException(message);
        }
    }

    private static Document retrieveDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .get();
    }

    private static String retrieveJson(String url) throws IOException {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .execute()
                .body();
    }
}
