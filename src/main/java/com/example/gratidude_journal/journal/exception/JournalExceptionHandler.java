package com.example.gratidude_journal.journal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JournalExceptionHandler {

    @ExceptionHandler(EntryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String userNameTakenHandler(EntryAlreadyExistsException ex) {
        return ex.getMessage();
    }
}