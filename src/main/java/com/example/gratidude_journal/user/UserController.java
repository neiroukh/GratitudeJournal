package com.example.gratidude_journal.user;

import com.example.gratidude_journal.user.dto.NewUserDTO;
import com.example.gratidude_journal.user.dto.UpdateUserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;

/*
    Presentation Layer for User-API
*/
@RestController
public class UserController {
    private final UserService userService;

    private final UserModelAssembler userAssembler;

    public UserController(UserService userService, UserModelAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @GetMapping("/user/{userName}")
    public EntityModel<User> getUser(@PathVariable String userName) {
        User user = userService.getUserByUserName(userName);

        return userAssembler.toModel(user);
    }

    @DeleteMapping("/user/{userName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        userService.deleteUserByUserName(userName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{userName}")
    public EntityModel<User> updateUser(@PathVariable String userName, @RequestBody UpdateUserDTO updateUserDTO) {
        User user = userService.updateUser(userName, updateUserDTO);

        return userAssembler.toModel(user);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<User> createUser(@RequestBody NewUserDTO newUserDTO) {
        User user = userService.createUser(newUserDTO);

        return userAssembler.toModel(user);
    }
}