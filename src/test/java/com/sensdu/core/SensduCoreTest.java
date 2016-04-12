package com.sensdu.core;

import static org.junit.Assert.*;

public class SensduCoreTest {

    @org.junit.Test
    public void testGetTranslation() throws Exception {
        assertEquals("Myky", new SensduCore("Dumpling", "en2fi").getTranslation());
        assertEquals("Метрополітен", new SensduCore("Rapid transit", "en2uk").getTranslation());

        //new test to extent the functionality. Started on 07/04/2015.

        //The program should check not only the direct page but also should identify the redirects.
        //No page on "Аммиачная селитра" exits. But there is redirect to "Нитрат аммония".
        assertEquals("Ammonium nitrate", new SensduCore("Аммиачная селитра", "ru2en").getTranslation());

        //There is only 'Кріп пахучий" page on wikipedia.
        //assertEquals("Dill", new SensduCore("Кріп", "uk2en").getTranslation());


    }
}