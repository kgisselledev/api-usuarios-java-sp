package com.users.test.service;

import com.users.test.model.User;
import com.users.test.model.UserDTO;
import com.users.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<String> addUserDTO(UserDTO userDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEmail(user.getEmail());
        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Mensaje:", "Usuario creado exitosamente");

        return new ResponseEntity<>("Usuario creado exitosamente", headers, HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<String> addUser(User newUser) {

        String mensajeErrorClave = validatePassFormat(newUser.getPassword());


        if (mensajeErrorClave != null) {
            return new ResponseEntity<>("{\"mensaje\": \"" + mensajeErrorClave + "\"}", HttpStatus.BAD_REQUEST);
        }


        if (ifEmailExist(newUser.getEmail())) {
            return new ResponseEntity<>("{\"mensaje\": \"El correo ya está registrado.\"}", HttpStatus.BAD_REQUEST);
        }

        if (!validateEmail(newUser.getEmail())) {
            return new ResponseEntity<>("{\"mensaje\": \"El formato del correo no es válido.\"}", HttpStatus.BAD_REQUEST);
        }

        try {

            User usuario = new User();

            usuario.setUsername(newUser.getUsername());
            usuario.setPassword(newUser.getPassword());
            usuario.setName(newUser.getName());
            usuario.setEmail(newUser.getEmail());
            usuario.setNumber(newUser.getNumber());
            usuario.setCitycode(usuario.getCitycode());
            usuario.setCountrycode(usuario.getCountrycode());

            userRepository.save(usuario);


            return new ResponseEntity<>("{\"mensaje\": \"Usuario creado exitosamente.\"}", HttpStatus.CREATED);

        } catch (Exception e) {
            String errorMessage = "{\"mensaje\": \"" + e.getMessage() + "\"}";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User editUser(long id, User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<String> removeUser(long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("El usuario con el ID: " + id + " no existe.");
        }

        userRepository.deleteById(id);

        return ResponseEntity.ok("Usuario con el ID: " + id + " ha sido eliminado exitosamente.");

    }

    private boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }

    private boolean ifEmailExist(String email) {

        return userRepository.existsByEmail(email);
    }

    private String validatePassFormat(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

        if (!password.matches(regex)) {

            return "La contraseña debe tener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula y un dígito.";
        }
        return null;
    }

}
