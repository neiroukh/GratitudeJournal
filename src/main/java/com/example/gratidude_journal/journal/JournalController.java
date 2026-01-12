package com.example.gratidude_journal.journal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
    Presentation Layer for Journal-API
*/
@RestController
public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/journal/{userName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@PathVariable String userName, @RequestBody JournalEntry newEntry) {
        journalService.addEntry(userName, newEntry);
    }
}