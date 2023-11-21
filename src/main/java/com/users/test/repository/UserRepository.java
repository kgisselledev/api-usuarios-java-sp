package com.users.test.repository;

import com.users.test.model.User;
import com.users.test.model.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);

    boolean existsByEmail(String email);


}
