package com.sensdu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticControllers {

        @RequestMapping(value="/")
        public String main() {
            return "views/input.html";
        }

        @RequestMapping(value="/output")
            public String output() {
            return "views/output.html";
        }


}