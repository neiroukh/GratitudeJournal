package com.example.gratidude_journal.user;

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
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/*
    Presentation Layer for User-API
*/
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userName}")
    public EntityModel<User> getUser(@PathVariable String userName) {
        User user = userService.getUserByUserName(userName);

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(userName)).withSelfRel(),
                linkTo(methodOn(UserController.class).deleteUser(userName)).withRel("delete"),
                linkTo(methodOn(UserController.class).updateUser(null)).withRel("update"),
                linkTo(methodOn(UserController.class).createUser(null)).withRel("create"));
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<RepresentationModel<?>> deleteUser(@PathVariable String userName) {
        userService.deleteUserByUserName(userName);

        RepresentationModel<?> model = new RepresentationModel<>();
        model.add(linkTo(methodOn(UserController.class).deleteUser(userName)).withSelfRel());
        model.add(linkTo(methodOn(UserController.class).createUser(null)).withRel("create"));
        return ResponseEntity.ok(model);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<User> createUser(@RequestBody User newUser) {
        User user = userService.saveUser(newUser);

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).createUser(null)).withSelfRel(),
                linkTo(methodOn(UserController.class).deleteUser(user.getUserName())).withRel("delete"),
                linkTo(methodOn(UserController.class).updateUser(null)).withRel("update"),
                linkTo(methodOn(UserController.class).getUser(user.getUserName())).withRel("get"));
    }

    @PutMapping("/user")
    public EntityModel<User> updateUser(@RequestBody User updatedUser) {
        User user = userService.updateUser(updatedUser);

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).createUser(null)).withRel("create"),
                linkTo(methodOn(UserController.class).deleteUser(user.getUserName())).withRel("delete"),
                linkTo(methodOn(UserController.class).updateUser(null)).withSelfRel(),
                linkTo(methodOn(UserController.class).getUser(user.getUserName())).withRel("get"));
    }
}