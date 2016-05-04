package com.sensdu.controllers;

import com.sensdu.core.SensduCore;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResourceController {

    @RequestMapping(value="/resource")
    public @ResponseBody Map<String,Object> setData(@RequestBody SensduCore sensdu) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("word", sensdu.getSourceWord());
        model.put("translatedWord", sensdu.getTranslatedWord());
        model.put("translatedWordURL", sensdu.getTranslatedWordURL());
        return model;
    }

}
