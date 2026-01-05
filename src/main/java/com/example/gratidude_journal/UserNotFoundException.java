package com.example.gratidude_journal;

class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String userName) {
        super("Could not find the user \"" + userName + "\"");
    }
}