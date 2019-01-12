package backend.controller;

import backend.model.dto.UserDto;
import backend.service.UserService;
import backend.service.interfaces.IUserService;
import java.util.Collection;
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

  private final IUserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<UserDto> findAll() {
    return service.findAll();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto save(@RequestBody UserDto body) {
    return service.save(body);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto edit(@PathVariable Long id, @RequestBody UserDto body) {
    return service.edit(id, body);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    service.deleteById(id);
  }
}