package com.example.gratitude_journal.user.exception;

/**
 * Exception thrown when an invalid name is provided.
 * 
 * @author Afeef Neiroukh
 */
public class NameInvalidException extends RuntimeException {
    /**
     * Constructor of the NameInvalidException class.
     * 
     * @param name The invalid name to mention in the Exception message.
     */
    public NameInvalidException(String name) {
        super("The name \"" + name + "\" does not follow the naming rules.");
    }
}