package com.sensdu.core;

import org.junit.Test;

import com.sensdu.domain.TranslationQuery;

import static org.junit.Assert.assertEquals;

public class SensduTests {

    @Test
    public void easyTranslationTest() throws Exception {
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setFromLanguage("en");
        translationQuery.setToLanguage("de");
        translationQuery.setWord("Big Bang");

        Sensdu sensdu = new Sensdu(translationQuery);

        assertEquals("Urknall", sensdu.retrieveTranslationResponse().getTranslation());
    }

    @Test
    public void translationWithRedirectTest() throws Exception {
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setFromLanguage("en");
        translationQuery.setToLanguage("ja");
        translationQuery.setWord("Landmass");

        Sensdu sensdu = new Sensdu(translationQuery);

        assertEquals("地", sensdu.retrieveTranslationResponse().getTranslation());
    }
}