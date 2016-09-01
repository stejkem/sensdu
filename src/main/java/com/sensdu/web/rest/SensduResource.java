package com.sensdu.web.rest;

import com.sensdu.core.SensduCore;
import com.sensdu.domain.Query;
import com.sensdu.repository.QueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SensduResource {
    private final Logger log = LoggerFactory.getLogger(SensduResource.class);

    @Inject
    private QueryRepository queryRepository;

    @RequestMapping(value="/resource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> setData(@Valid @RequestBody SensduCore sensdu) {
        Map<String, Object> model = new HashMap<>();
        model.put("sourceWord", sensdu.getSourceWord());

        Query userQuery = new Query();
        userQuery.setSourceWord(sensdu.getSourceWord());


        userQuery.setToLanguage("to");
        userQuery.setSourceWordWikiUrl("URL");
        userQuery.setTranslatedWord("t");
        userQuery.setTranslatedWordWikiUrl("t URL");
        System.err.println("REST request to save Query : {}" + userQuery);
        queryRepository.save(userQuery);
        try {
            if (sensdu.isDisambiguationArticle() && !sensdu.getState().equals("ambiguousArticle") ) {
                model.put("wordSuggestion", sensdu.getSourceWordSearchSuggestion());
                model.put("toLanguage", sensdu.getToLanguage());
//                userQuery.setToLanguage(sensdu.getToLanguage());
                model.put("fromLanguage", sensdu.getFromLanguage());
                model.put("state", "ambiguousArticle");
            } else {
                try {
                    model.put("translatedWord", sensdu.getTranslatedWord());
                    model.put("translatedWordURL", sensdu.getTranslatedWordWikiUrl());
                    model.put("toLanguage", sensdu.getToLanguage());
                    model.put("fromLanguage", sensdu.getFromLanguage());
                    model.put("wordURL", sensdu.getSourceWordWikiUrl());

                } catch (NullPointerException e) {
                    if (!sensdu.getState().equals("ambiguousArticle") && sensdu.getSourceWordSearchSuggestion().size() != 0) {
                        model.put("wordSuggestion", sensdu.getSourceWordSearchSuggestion());
                        model.put("toLanguage", sensdu.getToLanguage());
                        model.put("fromLanguage", sensdu.getFromLanguage());
                        model.put("state", "ambiguousArticle");
                    } else {
                        model.put("toLanguage", sensdu.getToLanguage());
                        model.put("fromLanguage", sensdu.getFromLanguage());
                        model.put("wordURL", sensdu.getSourceWordWikiUrl());
                    }
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(model);
    }

}
