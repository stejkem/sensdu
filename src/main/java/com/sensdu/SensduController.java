package com.sensdu;

import com.sensdu.core.SensduCore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SensduController {
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String tranlsationForm(Model model) {
        model.addAttribute("sensduCore", new SensduCore());
        return "home";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String translationSubmitted(@ModelAttribute SensduCore sensduCore, Model model) {
        model.addAttribute("sensduCore", sensduCore);
        return "result";
    }

}
