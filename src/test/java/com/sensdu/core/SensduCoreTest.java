package com.sensdu.core;

import static org.junit.Assert.*;

public class SensduCoreTest {

    @org.junit.Test
    public void testGetTranslation() throws Exception {
        assertEquals("Myky", new SensduCore("Dumpling", "en2fi").getTranslation());
        assertEquals("Метрополітен", new SensduCore("Rapid transit", "en2uk").getTranslation());

        //new test to extent the functionality. Added 07/04/2015.

        //There is only 'Кріп пахучий" page on wikipedia.
        //assertEquals("Dill", new SensduCore("Кріп", "uk2en").getTranslation());


    }
}