package com.ivansaprykin.testtasks.bostongene.yandextranslateapi.service;

import com.ivansaprykin.testtasks.bostongene.yandextranslateapi.model.YandexTranslateResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.ContentResponseHandler;

import java.io.IOException;

public class YandexTranslateResponseHandler implements ResponseHandler<YandexTranslateResponse> {

    @Override
    public YandexTranslateResponse handleResponse(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        ContentResponseHandler contentHandler = new ContentResponseHandler();

        return new YandexTranslateResponse(
                httpResponse.getStatusLine().getStatusCode(),
                contentHandler.handleEntity(entity).asString());
    }
}
