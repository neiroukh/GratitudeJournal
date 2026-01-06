package com.example.gratidude_journal;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    /*
    @GetMapping("/user")
    List<User> all() {
        return repository.findAll();
    }
    */

    @GetMapping("/user/{userName}")
    User getUserByUserName(@PathVariable String userName) {
        if (!User.validateName(userName))
            throw new NameInvalidException(userName);

        return repository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
    }

    @DeleteMapping("/user/{userName}")
    void deleteEmployee(@PathVariable String userName) {
        if (!User.validateName(userName))
            throw new NameInvalidException(userName);

        repository.findByUserName(userName)
                .ifPresentOrElse(
                        foundUser -> repository.deleteById(foundUser.getUserId()),
                        () -> {
                            throw new UserNotFoundException(userName);
                        });
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody User newUser) {
        if (repository.findByUserName(newUser.getUserName()).isPresent())
            throw new UserNameTakenException(newUser.getUserName());

        return repository.save(newUser);
    }

    @PutMapping("/user")
    User updateUser(@RequestBody User updatedUser) {
        return repository.findByUserName(updatedUser.getUserName())
                .map(foundUser -> {
                    foundUser.setFirstName(updatedUser.getFirstName());
                    foundUser.setLastName(updatedUser.getLastName());
                    return repository.save(foundUser);
                })
                .orElseThrow(() -> new UserNotFoundException(updatedUser.getUserName()));
    }
}