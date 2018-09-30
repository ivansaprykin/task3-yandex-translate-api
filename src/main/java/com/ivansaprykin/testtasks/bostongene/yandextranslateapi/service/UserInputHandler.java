package com.ivansaprykin.testtasks.bostongene.yandextranslateapi.service;

import java.util.Scanner;

public class UserInputHandler {

    private Scanner scanner;

    public UserInputHandler() {
        scanner  = new Scanner(System.in);
    }

    public String getUserInput() {
        while ( !  scanner.hasNext("[A-Za-z0-9\\p{P}\\p{S}\\s]+")) { // английские буквы, цифры, unicode знаки пунктуации и символы
            System.out.println("Incorrect input!");
            scanner.nextLine();
        }
        return  scanner.nextLine();
    }
}
