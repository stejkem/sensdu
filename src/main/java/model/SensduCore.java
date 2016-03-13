package model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by stejkem on 13/03/2016.
 */
public class SensduCore {
    /**
     * @param word the word that should be translated
     * @return sense-for-sense translation from Wiki
     */
    public String getTranslation(String word, String translationRequest) throws Exception{
        VectorOfTranslation vot = new VectorOfTranslation(translationRequest);
        URL requestToWiki = requestToWikipediaBuilder(word, vot);

        BufferedReader in = new BufferedReader(new InputStreamReader(requestToWiki.openStream()));
        StringBuilder answerFromWebSite = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            answerFromWebSite.append(inputLine);
        }

        Object jsonDocument = Configuration.defaultConfiguration().jsonProvider().parse(answerFromWebSite.toString());
        List<String> langsKey = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].lang");
        List<String> wordValues = JsonPath.read(jsonDocument, "$.query.pages[*].langlinks[*].title");
        Map<String, String> langsAndWords = new HashMap<String, String>();

        Iterator<String> langsKeyIter = langsKey.iterator();
        Iterator<String> wordValuesIter = wordValues.iterator();

        while (langsKeyIter.hasNext() && wordValuesIter.hasNext()) {
            langsAndWords.put(langsKeyIter.next(), wordValuesIter.next());
        }

        return langsAndWords.get(vot.getToLanguage());
    }

    private URL requestToWikipediaBuilder(String word, VectorOfTranslation vot) throws Exception {
        StringBuilder request = new StringBuilder();
        request.append("https://");
        request.append(vot.getFromLanguage());
        request.append(".wikipedia.org/w/api.php?action=query&titles=");
        request.append(word.replaceAll("\\s+","%20"));
        request.append("&prop=langlinks&formatversion=2&lllimit=500&format=json");

        return new URL(request.toString());
    }

}
