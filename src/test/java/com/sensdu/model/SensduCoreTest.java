package com.sensdu.model;

import static org.junit.Assert.*;

/**
 * Created by stejkem on 13/03/2016.
 */
public class SensduCoreTest {

    @org.junit.Test
    public void testGetTranslation() throws Exception {
        assertEquals("Myky", new SensduCore("Dumpling", "en2fi").getTranslation());
        assertEquals("Метрополітен", new SensduCore("Rapid transit", "en2uk").getTranslation());

    }
}