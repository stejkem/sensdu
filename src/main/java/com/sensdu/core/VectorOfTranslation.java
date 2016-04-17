package com.sensdu.core;

class VectorOfTranslation {
    private String fromLanguage;
    private String toLanguage;

    VectorOfTranslation(String translationRequest) throws Exception {
        translationRequest = translationRequest.toLowerCase();
        fromLanguage = translationRequest.substring(0, 2);
        toLanguage = translationRequest.substring(3, 5);
    }

    String getFromLanguage() {
        return fromLanguage;
    }

    String getToLanguage() {
        return toLanguage;
    }

}
