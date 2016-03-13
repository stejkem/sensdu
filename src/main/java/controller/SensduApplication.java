package controller;

import model.SensduCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dbykon on 13/03/2016.
 */

@SpringBootApplication
@RestController
public class SensduApplication {

    SensduCore sCore = new SensduCore();

    @RequestMapping("/resource")
    public Map<String, Object> home() throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("translation", sCore.getTranslation("Dumpling", "en2fi") );
        return model;
    }

    public static void main(String[] args) {
        SpringApplication.run(SensduApplication.class, args);
    }

}
