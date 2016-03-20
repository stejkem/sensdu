package com.sensdu.model;

import static org.junit.Assert.*;

/**
 * Created by dbykon on 13/03/2016.
 */
public class VectorOfTranslationTest {

    @org.junit.Test
    public void testCreateVectorOfTranslation() throws Exception {
        VectorOfTranslation vot = new VectorOfTranslation("en2fi");
        assertEquals("en", vot.getFromLanguage());
        assertEquals("fi", vot.getToLanguage());
    }

}