package com.sensdu.core;

import static org.junit.Assert.*;

public class SensduCoreTest {

    @org.junit.Test
    public void testGetTranslation() throws Exception {

        assertEquals("Myky", new SensduCore("Dumpling", "en", "fi").getTranslatedWord());
        assertEquals("Ammonium nitrate", new SensduCore("Аммиачная селитра", "ru", "en").getTranslatedWord());
        assertEquals("Cat", new SensduCore("Кіт свійський", "uk", "en").getTranslatedWord());
        assertEquals("Hauskatze", new SensduCore("Cat", "en", "de").getTranslatedWord());

    }
}