package com.users.test.service;

import com.users.test.model.User;
import com.users.test.model.UserDTO;
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
