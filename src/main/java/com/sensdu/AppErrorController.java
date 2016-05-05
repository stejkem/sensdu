package com.sensdu;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppErrorController implements ErrorController {
    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value=PATH)
    public String error405_500() {
        return "405.html";
    }

    @RequestMapping("/404")
    String error404() {
        return "404.html";
    }

}