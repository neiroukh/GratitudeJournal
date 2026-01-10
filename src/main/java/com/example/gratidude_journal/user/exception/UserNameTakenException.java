package com.example.gratidude_journal.user.exception;

public class UserNameTakenException extends RuntimeException {

    public UserNameTakenException(String userName) {
        super("The userName \"" + userName + "\" is already taken.");
    }
}