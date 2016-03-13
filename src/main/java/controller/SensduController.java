package controller;

import model.SensduCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dbykon on 13/03/2016.
 */
@Controller
@EnableAutoConfiguration
public class SensduController {

    SensduCore sCore = new SensduCore();

    @RequestMapping("/")
    @ResponseBody
    public String index() throws Exception {
        return sCore.getTranslation("Dumpling", "en2fi");
    }

    public static void main(String[] args) {
        SpringApplication.run(SensduController.class, args);
    }

}
