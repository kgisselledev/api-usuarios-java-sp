package com.users.test.controller;

import com.users.test.model.User;
import com.users.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControler {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user){

        return userService.addUser(user);

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id){

        return userService.findById(id);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable long id, @RequestBody User user){
        User changeUser = userService.editUser(id, user);
        return ResponseEntity.ok(changeUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> removeUser(@PathVariable long id){

        return userService.removeUser(id);
    }



}
