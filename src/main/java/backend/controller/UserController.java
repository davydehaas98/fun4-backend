package backend.controller;

import backend.model.User;
import backend.model.dto.UserDto;
import backend.repository.UserRepository;
import java.util.Collection;
import java.util.stream.Collectors;
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

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public UserController(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<UserDto> getUsers() {
    return userRepository.findAll()
        .stream().map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto getUserById(@PathVariable Long userId) {
    return modelMapper.map(
        userRepository.findById(userId),
        UserDto.class
    );
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public UserDto addUser(@RequestBody UserDto body) {
    return modelMapper.map(
        userRepository.save(new User(body.getUsername(), body.getPassword(), body.getTickets())),
        UserDto.class
    );
  }

  @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto changeUser(@RequestBody UserDto body, @PathVariable Long userId) {
    return modelMapper.map(
        userRepository.findById(userId)
            .map(u -> {
              u.setUsername(body.getUsername());
              u.setPassword(body.getUsername());
              return u;
            }),
        UserDto.class);
  }

  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable Long userId) {
    userRepository.deleteById(userId);
  }
}
