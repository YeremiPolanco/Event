package com.idat.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.demo.model.User;
import com.idat.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // obtener el emaik
        String userEmail = user.getEmail();
        // saber si el email esta ya usado
        boolean isEmailAlreadyExists = userService.exists(userEmail);
        // validacion del metodo exists y devolver un response
        if (isEmailAlreadyExists == true) {
            return ResponseEntity.badRequest().build();
        } else {
            User newUser = userService.saveUser(user);
            return ResponseEntity.ok(newUser);
        }
        // if (userService.exists(user.getEmail()))
        // return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User body) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setEmail(body.getEmail());
        user.setName(body.getName());
        return ResponseEntity.ok(userService.saveUser(user));
    }
}
