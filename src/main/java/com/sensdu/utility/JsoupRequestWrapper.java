package com.sensdu.utility;

import java.io.IOException;

import org.jsoup.Jsoup;

/**
 * According to the User-Agent policy -
 * <a> https://meta.wikimedia.org/wiki/User-Agent_policy </a>
 * Wikimedia sites require a HTTP User-Agent header for all requests.
 *
 * @author Stejkem
 */
public class JsoupRequestWrapper {

    private JsoupRequestWrapper() {
    }

    public static String retrieveJson(String url) throws IOException {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Sensdu/1.0 (http://www.sensdu.com)")
                .referrer("http://www.sensdu.com")
                .timeout(12000)
                .execute()
                .body();
    }
}
