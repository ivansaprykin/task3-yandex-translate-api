package com.ivansaprykin.testtasks.bostongene.yandextranslateapi;

import com.ivansaprykin.testtasks.bostongene.yandextranslateapi.service.Translator;
import com.ivansaprykin.testtasks.bostongene.yandextranslateapi.service.UserInputHandler;

public class Main {

    private static final String TERMINATE_PROGRAM = "!x";
    public static void main(String[] args) {
        System.out.println("Please enter text in english to get russian translation. To exit program enter: " + TERMINATE_PROGRAM);

        UserInputHandler inputHandler = new UserInputHandler();
        Translator translator = new Translator();

        String userInput = inputHandler.getUserInput();
        while ( ! userInput.equals(TERMINATE_PROGRAM)) {

            System.out.println(translator.translate(userInput));
            userInput = inputHandler.getUserInput();
        }
    }
}
