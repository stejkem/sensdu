package model;

/**
 * Created by dbykon on 13/03/2016.
 */
class VectorOfTranslation {
    private String fromLanguage;
    private String toLanguage;

    public VectorOfTranslation(String translationRequest) throws Exception {
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
