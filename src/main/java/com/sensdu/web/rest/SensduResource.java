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
import java.sql.Timestamp;
import java.util.Date;
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
        userQuery.setUserAgent(sensdu.getUserAgent());
        Date date = new Date();
        userQuery.setDate(new Timestamp(date.getTime()));
        try {
            if (sensdu.isDisambiguationArticle() && !sensdu.getState().equals("ambiguousArticle") ) {
                model.put("wordSuggestion", sensdu.getSourceWordSearchSuggestion());
                model.put("toLanguage", sensdu.getToLanguage());
                model.put("fromLanguage", sensdu.getFromLanguage());
                model.put("state", "ambiguousArticle");
            } else {
                try {
                    model.put("fromLanguage", sensdu.getFromLanguage());
                    model.put("toLanguage", sensdu.getToLanguage());
                    model.put("wordURL", sensdu.getSourceWordWikiUrl());
                    model.put("translatedWord", sensdu.getTranslatedWord());
                    model.put("translatedWordURL", sensdu.getTranslatedWordWikiUrl());

                    userQuery.setToLanguage(sensdu.getToLanguage());
                    userQuery.setFromLanguage(sensdu.getFromLanguage());
                    userQuery.setSourceWordWikiUrl(sensdu.getSourceWordWikiUrl());
                    userQuery.setTranslatedWord(sensdu.getTranslatedWord());
                    userQuery.setTranslatedWordWikiUrl(sensdu.getTranslatedWordWikiUrl());
                    userQuery.setSearchRoutine("translated");
                    queryRepository.save(userQuery);

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
                        userQuery.setToLanguage(sensdu.getToLanguage());
                        userQuery.setFromLanguage(sensdu.getFromLanguage());
                        userQuery.setSourceWordWikiUrl(sensdu.getSourceWordWikiUrl());
                        userQuery.setSearchRoutine("no translation available");
                        queryRepository.save(userQuery);
                    }
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(model);
    }

}
