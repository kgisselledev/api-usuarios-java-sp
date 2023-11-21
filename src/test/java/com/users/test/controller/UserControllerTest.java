package com.users.test.controller;

import com.users.test.model.User;
import com.users.test.model.UserDTO;
import com.users.test.repository.UserRepository;
import com.users.test.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUser(){
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.findAll();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testAddUserDTO() {

        when(userRepository.save(any(User.class))).thenReturn(new User());

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("karol_ramirez");
        userDTO.setPassword("noviembre2023");

        ResponseEntity<String> result = userService.addUserDTO(userDTO);

        verify(userRepository, times(1)).save(any(User.class));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Usuario creado exitosamente", result.getBody());
        assertTrue(result.getHeaders().containsKey("Mensaje:"));
    }



    @Test
    void testRemoveUser() {

        when(userRepository.existsById(any(Long.class))).thenReturn(true);


        ResponseEntity<String> result = userService.removeUser(1L);


        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);

    }


}
