package com.sensdu.service;

import com.sensdu.Application;
import org.junit.Test;

import com.sensdu.domain.TranslationQuery;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TranslationServiceTests {

    @Autowired
    private TranslationService translationService;

    @Test
    public void translationWithRedirectTest() throws Exception {
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setFromLanguage("en");
        translationQuery.setToLanguage("ja");
        translationQuery.setWord("Landmass");

        translationService.setTranslationQuery(translationQuery);

        assertEquals("地", translationService.retrieveTranslationResponse().getTranslation());
    }
}
