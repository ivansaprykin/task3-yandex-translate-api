package com.ivansaprykin.testtasks.bostongene.yandextranslateapi.service;

import com.ivansaprykin.testtasks.bostongene.yandextranslateapi.model.YandexTranslateResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Optional;

public class Translator {

    private final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-ru&key=trnsl.1.1.20180929T182904Z.04ae991239ce7c70.ed74d48c471924265df9fbc510cfa67ad197f822";

    private String translatorErrorMessage;
    public String getTranslatorErrorMessage() { return translatorErrorMessage; }
    public void setTranslatorErrorMessage(String translatorErrorMessage) { this.translatorErrorMessage = translatorErrorMessage; }

    public String translate(String textToTranslate) {
        return processResponse(sendTextToTranslate(textToTranslate));
    }

    private Optional<YandexTranslateResponse> sendTextToTranslate(String text) {
        Optional<YandexTranslateResponse> optionalResponse = Optional.empty();
        try {
            optionalResponse = Optional.of(Request.Post(URL)
                    .useExpectContinue()
                    .bodyString("text=" + text, ContentType.APPLICATION_FORM_URLENCODED)
                    .execute().handleResponse(new YandexTranslateResponseHandler()));
        } catch (IOException e) {
            setTranslatorErrorMessage("Can't send text to yandex api. " + e.getMessage());
        }

        return optionalResponse;
    }

    private String processResponse(Optional<YandexTranslateResponse> optionalResponse) {
        if(optionalResponse.isPresent()) {
            YandexTranslateResponse translateResponse = optionalResponse.get();
            if(translateResponse.getStatus() == HttpStatus.SC_OK) {
                return translateResponse.getTranslation();
            } else {
                return translateResponse.getErrorMessage();
            }
        } else {
            return getTranslatorErrorMessage();
        }
    }
}
