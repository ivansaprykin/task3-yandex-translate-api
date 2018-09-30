package com.ivansaprykin.testtasks.bostongene.yandextranslateapi.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.List;

/**
 * {"code":401,"message":"API key is invalid"}
 * {"code":200,"lang":"en-ru","text":["привет"]}
 */
public class YandexTranslateResponse {

    private static final String YANDEX_REQUIREMENT = "\nPowered by Yandex.Translate  http://translate.yandex.com/";

    private int status;
    private String jsonBody;
    private ObjectMapper mapper;

    public YandexTranslateResponse(int status, String jsonBody) {
        this.status = status;
        this.jsonBody = jsonBody;
        mapper = new ObjectMapper();
    }

    public int getStatus() {
        return status;
    }

    public String getTranslation()   {
        //List<MyClass> myObjects = Arrays.asList(mapper.readValue(json, MyClass[].class))
        SucsessResponse sucsessResponse = null;
        try {
            sucsessResponse = mapper.readValue(jsonBody, SucsessResponse.class);
        } catch (IOException e) {
            return "JSON response format has changed!" + e.getMessage();
        }
        return sucsessResponse.getText().stream().reduce((t, u) -> t + "\n" + u).get() + YANDEX_REQUIREMENT; //.stream().reduce((t, u) -> t + "," + u).get()
    }

    public String getErrorMessage()   {
        ErrorResponse errorResponse = null;
        try {
            errorResponse = mapper.readValue(jsonBody, ErrorResponse.class);
        } catch (IOException e) {
            return "JSON response format has changed!" + e.getMessage();
        }
        return errorResponse.toString() + YANDEX_REQUIREMENT;
    }


    private static class ErrorResponse{
        private int code;
        private String message;

        public ErrorResponse() { }
        public int getCode() { return code; }
        public void setCode(int code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        @Override
        public String toString() { return "Error! code=" + code + "; message='" + message + '\''; }
    }

    private static class SucsessResponse{
        private int code;
        private String lang;
        private List<String> text;

        public SucsessResponse() { }
        public int getCode() { return code; }
        public void setCode(int code) { this.code = code; }
        public String getLang() { return lang; }
        public void setLang(String lang) { this.lang = lang; }
        public List<String> getText() { return text; }
        public void setText(List<String> text) { this.text = text; }
    }

}
