package com.sensdu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TranslatorController {
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String tranlsationForm(Model model) {
        model.addAttribute("translator", new Translator());
        return "home";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String translationSubmitted(@ModelAttribute Translator translator, Model model) {
        model.addAttribute("translator", translator);
        return "result";
    }

}
