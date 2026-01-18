package com.example.gratidude_journal.user;

import com.example.gratidude_journal.journal.JournalController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(user.getUserName())).withSelfRel(),
                linkTo(methodOn(UserController.class).updateUser(user.getUserName(), null)).withRel("update"),
                linkTo(methodOn(UserController.class).deleteUser(user.getUserName())).withRel("delete"),
                linkTo(methodOn(JournalController.class).getEntries(user.getUserName())).withRel("journal"));
    }
}