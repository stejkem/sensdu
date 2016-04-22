package com.sensdu.core;

public class VectorOfTranslation {
    private String fromLanguage;
    private String toLanguage;

    public VectorOfTranslation(String translationRequest) throws Exception {
        translationRequest = translationRequest.toLowerCase();
        if (!translationRequest.substring(0,1).matches("\\w+")) {
            translationRequest = VOTMap.ALIASES.get(translationRequest);
        }
        fromLanguage = translationRequest.substring(0, 2);
        toLanguage = translationRequest.substring(3, 5);

    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public String getToLanguage() {
        return toLanguage;
    }

}
