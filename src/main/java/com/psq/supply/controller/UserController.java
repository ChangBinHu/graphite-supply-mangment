package com.psq.supply.controller;

import com.psq.supply.entity.User;
import com.psq.supply.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:27
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
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
