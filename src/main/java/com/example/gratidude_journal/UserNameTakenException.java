package com.example.gratidude_journal;

class UserNameTakenException extends RuntimeException {

    UserNameTakenException(String userName) {
        super("The userName \"" + userName + "\" is already taken.");
    }
}