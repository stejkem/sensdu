package com.sensdu;

import com.sensdu.core.SensduCore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestControllers {

    @RequestMapping(value="/resource")
    public ResponseEntity<Map<String, Object>> setData(@RequestBody SensduCore sensdu) {
        Map<String, Object> model = new HashMap<>();
        model.put("sourceWord", sensdu.getSourceWord());
        try {
            if (sensdu.isDisambiguationArticle()) {
                throw new NullPointerException();
            } else {
                model.put("state", "traslated");
                model.put("toLanguage", sensdu.getToLanguage());
                model.put("fromLanguage", sensdu.getFromLanguage());
                model.put("wordURL", sensdu.getSourceWordlURL());
                model.put("translatedWord", sensdu.getTranslatedWord());
                model.put("translatedWordURL", sensdu.getTranslatedWordURL());
            }
        } catch (Exception firstE) {
                if(firstE.getClass().equals(NullPointerException.class)) {
                    try {
                        model.put("state", "disambiguated");
                        model.put("toLanguage", sensdu.getToLanguage());
                        model.put("fromLanguage", sensdu.getFromLanguage());
                        model.put("wordSuggestion", sensdu.getSourceWordSearchSuggestion());
                    } catch (Exception secondE) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                    }
                }
        }
        return ResponseEntity.ok(model);
    }

}
