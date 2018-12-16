package com.fun4.checkers.controller;

import com.fun4.checkers.model.User;
import com.fun4.checkers.model.dto.UserDto;
import com.fun4.checkers.repository.UserRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;

  UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @PostMapping
  public User addUser(@RequestBody UserDto body) {
    return userRepository.save(new User(body.getUsername(), body.getPassword()));
  }

  @GetMapping("/{userId}")
  public Optional<User> getUserById(@PathVariable Long userId) {
    return userRepository.findById(userId);
  }

  @PutMapping("/{userId}")
  public User changeUser(@RequestBody UserDto body, @PathVariable Long userId) {
    return userRepository.findById(userId)
        .map(user -> {
              user.setUsername(body.getUsername());
              user.setPassword(body.getPassword());
              return userRepository.save(user);
            }
        ).orElseThrow();
  }

  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable Long userId) {
    userRepository.deleteById(userId);
  }
}
