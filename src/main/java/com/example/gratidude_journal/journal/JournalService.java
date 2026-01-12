package com.example.gratidude_journal.journal;

import com.example.gratidude_journal.user.User;

import com.example.gratidude_journal.user.UserService;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

/*
    Service Layer for Journal-API
*/
@Service
public class JournalService {
    private final UserService userService;

    public JournalService(UserService userService) {
        this.userService = userService;
    }

    public void addEntry(String userName, JournalEntry newEntry) {
        User user = userService.getUserByUserName(userName);

        user.addJournalEntry(newEntry);
        userService.saveUser(userName);
    }

    public JournalEntry getEntry(String userName, LocalDate date) {
        User user = userService.getUserByUserName(userName);

        return user.getJournal().getJournalEntries().stream().filter(entry -> entry.getDate().equals(date)).findFirst()
                .get();
    }
}