package com.sensdu.controllers;

import com.sensdu.core.SensduCore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResourceController {

    @RequestMapping(value="/resource", method = RequestMethod.GET)
    public Map<String,Object> setData(String sourceWord, String fromLanguage, String toLanguage) throws Exception {
        Map<String, Object> model = new HashMap<>();
        SensduCore sesndu = new SensduCore(sourceWord, fromLanguage, toLanguage);
        model.put("sourceWord", sesndu.getSourceWord());
        model.put("translatedWord", sesndu.getTranslatedWord());
        model.put("translatedWordURL", sesndu.getTranslatedWordURL());
        return model;
    }

//    @RequestMapping(value="/resource", method = RequestMethod.GET)
//    public Map<String,Object> getData(@RequestBody String sourceWord, String fromLanguage, String toLanguage) throws Exception {
//        Map<String, Object> model = new HashMap<>();
//        SensduCore sesndu = new SensduCore(sourceWord, fromLanguage, toLanguage);
//        model.put("sourceWord", sesndu.getSourceWord());
//        model.put("translatedWord", sesndu.getTranslatedWord());
//        model.put("translatedWordURL", sesndu.getTranslatedWordURL());
//        return model;
//    }
//
//

}
