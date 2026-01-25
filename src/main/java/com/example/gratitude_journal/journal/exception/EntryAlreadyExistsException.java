package com.example.gratitude_journal.journal.exception;

import java.time.LocalDate;

/**
 * Exception thrown when a journal entry already exists for a certain date.
 */
public class EntryAlreadyExistsException extends RuntimeException {
    /**
     * Public constructor of the EntryAlreadyExistsException class.
     * 
     * @param date The date of conflict.
     */
    public EntryAlreadyExistsException(LocalDate date) {
        super("An entry already exists for " + date.toString());
    }
}