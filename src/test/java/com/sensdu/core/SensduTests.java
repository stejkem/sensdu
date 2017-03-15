package com.sensdu.core;

import org.junit.Before;
import org.junit.Test;

import com.sensdu.domain.TranslationQuery;

import static org.junit.Assert.assertEquals;

public class SensduTests {

    Sensdu sensdu;

    @Before
    public void init() throws Exception {
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setFromLanguage("en");
        translationQuery.setToLanguage("de");
        translationQuery.setWord("Big Bang");

        sensdu = new Sensdu(translationQuery);
    }

    @Test
    public void testTranslation() throws Exception {
        assertEquals("Urknall", sensdu.retrieveTranslationResponse().getTranslation());
    }
}