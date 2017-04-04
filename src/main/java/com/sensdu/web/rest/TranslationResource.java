package com.sensdu.web.rest;

import com.sensdu.domain.TranslationQuery;
import com.sensdu.domain.TranslationResponse;
import com.sensdu.service.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TranslationResource {

    private final Logger logger = LoggerFactory.getLogger(TranslationResource.class);

    @Autowired
    private TranslationService translationService;

    @PostMapping("/translate")
    public ResponseEntity<TranslationResponse> getTranslation(@Valid @RequestBody TranslationQuery translationQuery) {
        logger.info("Translating for the following query is in progress - " + translationQuery);
        translationService.setTranslationQuery(translationQuery);

        return new ResponseEntity<>(translationService.retrieveTranslationResponse(), HttpStatus.OK) ;
    }
}
