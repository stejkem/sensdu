package com.sensdu;

import com.sensdu.core.SensduCore;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestControllers {

    @RequestMapping(value="/resource")
    public @ResponseBody Map<String,Object> setData(@RequestBody SensduCore sensdu) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("word", sensdu.getSourceWord());
        model.put("wordURL", sensdu.getSourceWordlURL());
        model.put("translatedWord", sensdu.getTranslatedWord());
        model.put("translatedWordURL", sensdu.getTranslatedWordURL());
        return model;
    }

}
