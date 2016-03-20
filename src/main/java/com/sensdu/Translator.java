package com.sensdu;

import com.sensdu.model.SensduCore;

class Translator {
    private SensduCore sensduCore = new SensduCore();

    private String sourceWord;
    private String vectorOfTranslation;
    private String translation;


    public String getVectorOfTranslation() {
        return vectorOfTranslation;
    }

    public void setVectorOfTranslation(String vectorOfTranslation) {
        this.vectorOfTranslation = vectorOfTranslation;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setSourceWord(String sourceWord) {
        this.sourceWord = sourceWord;
    }

    public String getTranslation() throws Exception {
        return sensduCore.getTranslation(sourceWord, vectorOfTranslation);
    }


}
