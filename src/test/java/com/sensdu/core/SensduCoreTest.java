package com.sensdu.core;

import static org.junit.Assert.*;

public class SensduCoreTest {

    @org.junit.Test
    public void testGetTranslation() throws Exception {
        assertEquals("Myky", new SensduCore("Dumpling", "en2fi").getTranslation());
        assertEquals("Ammonium nitrate", new SensduCore("Аммиачная селитра", "ru2en").getTranslation());
        assertEquals("Cat", new SensduCore("Кіт свійський", "укр2англ").getTranslation());

        //There is only 'Кріп пахучий" page on wikipedia.
        //assertEquals("Dill", new SensduCore("Кріп", "uk2en").getTranslation());


    }
}