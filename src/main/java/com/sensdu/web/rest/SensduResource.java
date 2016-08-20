package com.sensdu.web.rest;

import com.sensdu.core.SensduCore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SensduResource {

    @RequestMapping(value="/resource")
    public ResponseEntity<Map<String, Object>> setData(@RequestBody SensduCore sensdu) {
        Map<String, Object> model = new HashMap<>();
        model.put("sourceWord", sensdu.getSourceWord());
        try {
            if (sensdu.isDisambiguationArticle() && !sensdu.getState().equals("ambiguousArticle") ) {
                model.put("wordSuggestion", sensdu.getSourceWordSearchSuggestion());
                model.put("toLanguage", sensdu.getToLanguage());
                model.put("fromLanguage", sensdu.getFromLanguage());
                model.put("state", "ambiguousArticle");
            } else {
                try {
                    model.put("translatedWord", sensdu.getTranslatedWord());
                    model.put("translatedWordURL", sensdu.getTranslatedWordURL());
                    model.put("toLanguage", sensdu.getToLanguage());
                    model.put("fromLanguage", sensdu.getFromLanguage());
                    model.put("wordURL", sensdu.getSourceWordlURL());
                } catch (NullPointerException e) {
                    if (!sensdu.getState().equals("ambiguousArticle") && sensdu.getSourceWordSearchSuggestion().size() != 0) {
                        model.put("wordSuggestion", sensdu.getSourceWordSearchSuggestion());
                        model.put("toLanguage", sensdu.getToLanguage());
                        model.put("fromLanguage", sensdu.getFromLanguage());
                        model.put("state", "ambiguousArticle");
                    } else {
                        throw new NullPointerException();
                    }
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(model);
    }

}
