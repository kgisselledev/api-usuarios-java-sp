package com.users.testusers.service;

import com.users.testusers.model.User;
import com.users.testusers.model.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(long id);
    User findByUsername(String username);
    ResponseEntity<String> addUserDTO(UserDTO userDTO);
    ResponseEntity<?> addUser(User newUser);
    User editUser(long id, User user);
    ResponseEntity<String> removeUser(long id);
}
