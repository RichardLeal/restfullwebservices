package com.rest.webservices.restfulwebservices.controllers.user;

import com.rest.webservices.restfulwebservices.exceptions.user.UserNotFoundException;
import com.rest.webservices.restfulwebservices.models.user.User;
import com.rest.webservices.restfulwebservices.services.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping( "/users" )
    public List<User> retriveAllUsers() {
        return userDAOService.findAll();
    }

    @GetMapping( "/users/{id}" )
    public User retriveUser(@PathVariable int id) {

        User retrivedUser = userDAOService.findOne(id);

        if(retrivedUser == null)
            throw new UserNotFoundException("undefined user of id " + id + " to retrive");

        // HATEOAS: https://medium.com/@mellomatheuslima/a-import%C3%A2ncia-do-hateoas-em-apis-restful-1ca2dc081288

        return retrivedUser;
    }

    @PostMapping( "/users" )
    public ResponseEntity<Object> createUser(@Valid @RequestBody User newUser) {

        User savedUser = userDAOService.save(newUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping( "/users/{id}" )
    public void deleteUser(@PathVariable int id) {

        User deletedUser = userDAOService.deleteById(id);

        if(deletedUser == null)
            throw new UserNotFoundException("undefined user of id " + id + " to delete");

    }

}
