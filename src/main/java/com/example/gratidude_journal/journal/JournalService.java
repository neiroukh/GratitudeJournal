package com.example.gratidude_journal.journal;

import com.example.gratidude_journal.user.User;

import com.example.gratidude_journal.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/*
    Service Layer for Journal-API
*/
@Service
public class JournalService {
    private final UserService userService;

    public JournalService(UserService userService) {
        this.userService = userService;
    }

    public void addEntry(@PathVariable String userName, @RequestBody JournalEntry newEntry) {
        User user = userService.getUserByUserName(userName);

        user.addJournalEntry(newEntry);
        userService.saveUser(userName);
    }
}