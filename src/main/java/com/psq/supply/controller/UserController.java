package com.psq.supply.controller;

import com.psq.supply.entity.User;
import com.psq.supply.repository.UserRepository;
import com.psq.supply.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:27
 **/
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public boolean createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/login")
    public boolean createUser( @RequestParam String userName,
                               @RequestParam String password) {
        return userService.login(userName, password);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
