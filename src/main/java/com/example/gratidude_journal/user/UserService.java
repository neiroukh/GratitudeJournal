package com.example.gratidude_journal.user;

import org.springframework.stereotype.Service;

import com.example.gratidude_journal.user.exception.NameInvalidException;
import com.example.gratidude_journal.user.exception.UserNameTakenException;
import com.example.gratidude_journal.user.exception.UserNotFoundException;

/*
    Service Layer for User-API
*/
@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private void validateName(String userName) {
        if (!User.validateName(userName))
            throw new NameInvalidException(userName);
    }

    private void validateName(User user) {
        validateName(user.getUserName());
        validateName(user.getFirstName());
        validateName(user.getLastName());
    }

    public User getUserByUserName(String userName) {
        validateName(userName);

        User user = repository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));

        return user;
    }

    public void deleteUserByUserName(String userName) {
        validateName(userName);

        repository.findByUserName(userName)
                .ifPresentOrElse(
                        foundUser -> repository.deleteById(foundUser.getUserId()),
                        () -> {
                            throw new UserNotFoundException(userName);
                        });
    }

    public User createUser(User newUser) {
        validateName(newUser);

        if (repository.findByUserName(newUser.getUserName()).isPresent())
            throw new UserNameTakenException(newUser.getUserName());

        return repository.save(newUser);
    }

    public User updateUser(User updatedUser) {
        validateName(updatedUser);

        return repository.findByUserName(updatedUser.getUserName())
                .map(foundUser -> {
                    foundUser.setFirstName(updatedUser.getFirstName());
                    foundUser.setLastName(updatedUser.getLastName());
                    return repository.save(foundUser);
                })
                .orElseThrow(() -> new UserNotFoundException(updatedUser.getUserName()));
    }

    public User saveUser(String userName) {
        validateName(userName);

        return repository.save(getUserByUserName(userName));
    }
}