package com.sensdu.utility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sensdu.config.AppConfig;

import static org.junit.Assert.assertEquals;

/**
 * @author Stejkem
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MediaWikiAPIRequestBuilderTest {

    @Test
    public void buildTranslationRequestURITest() throws Exception {
        String actualURL = "https://en.wikipedia.org/w/api.php?action=query&titles=Big Bang&redirects&prop=langlinks|info&formatversion=2&lllimit=500&llprop=url&inprop=url&format=json";
        assertEquals(actualURL, MediaWikiAPIRequestBuilder.buildTranslationRequestURI("Big Bang", "en"));
    }
}
