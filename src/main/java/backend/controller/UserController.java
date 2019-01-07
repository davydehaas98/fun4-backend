package backend.controller;

import backend.model.User;
import backend.model.dto.UserDto;
import backend.repository.UserRepository;
import backend.service.UserService;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
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

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<UserDto> findAll() {
    return userService.findAll();
  }

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto findById(@PathVariable Long userId) {
    return userService.findById(userId);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public UserDto save(@RequestBody UserDto body) {
    return userService.save(body);
  }

  @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto edit(@PathVariable Long userId, @RequestBody UserDto body) {
    return userService.edit(userId, body);
  }

  @DeleteMapping("/{userId}")
  public void deleteById(@PathVariable Long userId) {
    userService.deleteById(userId);
  }
}