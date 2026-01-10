package com.example.gratidude_journal.journal;

import java.time.LocalDate;

public class EntryAlreadyExistsException extends RuntimeException {
    EntryAlreadyExistsException(LocalDate date) {
        super("An entry already exists for " + date.toString());
    }
}