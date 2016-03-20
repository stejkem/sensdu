package com.sensdu.model;

import static org.junit.Assert.*;

/**
 * Created by stejkem on 13/03/2016.
 */
public class SensduCoreTest {

    @org.junit.Test
    public void testGetTranslation() throws Exception {
        SensduCore sensduCore = new SensduCore();
        assertEquals("Myky", sensduCore.getTranslation("Dumpling", "en2fi"));
        assertEquals("Метрополітен", sensduCore.getTranslation("Rapid transit", "en2uk"));

    }
}