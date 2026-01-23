package com.example.gratitude_journal.user.exception;

/**
 * Exception thrown when the provided user name is already taken.
 * 
 * @author Afeef Neiroukh
 */
public class UserNameTakenException extends RuntimeException {

    /**
     * Constructor of the UserNameTakenException class.
     * 
     * @param userName The user name taken to mention in the Exception message.
     */
    public UserNameTakenException(String userName) {
        super("The userName \"" + userName + "\" is already taken.");
    }
}