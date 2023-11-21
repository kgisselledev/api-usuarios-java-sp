package com.users.testusers.repository;

import com.users.testusers.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);

    boolean existsByEmail(String email);


}
