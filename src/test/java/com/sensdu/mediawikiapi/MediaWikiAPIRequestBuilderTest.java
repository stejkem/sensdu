package com.sensdu.mediawikiapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;

import static org.junit.Assert.assertEquals;

/**
 * @author Stejkem
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MediaWikiAPIRequestBuilderTest {

    private MediaWikiAPIRequestBuilder mediaWikiEnglishAPI;

    @Before
    public void initialization(MediaWikiAPIRequestBuilder mediaWikiEnglishAPI) {
        this.mediaWikiEnglishAPI = mediaWikiEnglishAPI;
    }

    @Test
    public void buildTranslationRequestURITest() throws Exception {
        URI actualURI = new URI("https://en.wikipedia.org/w/api.php?action=query&titles=Big%20Bang&redirects&prop=langlinks&formatversion=2&lllimit=500&llprop=url&format=json");
        assertEquals(mediaWikiEnglishAPI.buildTranslationRequestURI("Big Bang"), actualURI);
    }

}