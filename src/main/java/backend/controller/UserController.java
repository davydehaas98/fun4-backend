package backend.controller;

import backend.model.User;
import backend.model.dto.UserDto;
import backend.service.UserService;
import backend.service.interfaces.IUserService;
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

    private final ModelMapper modelMapper;
    private final IUserService service;

    public UserController(ModelMapper modelMapper, UserService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto findById(@PathVariable Long id) {
        return modelMapper.map(service.findById(id), UserDto.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<UserDto> findAll() {
        return service.findAll()
            .stream().map(item ->
                modelMapper.map(item, UserDto.class)
            ).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto save(@RequestBody UserDto body) {
        return modelMapper.map(service.save(modelMapper.map(body, User.class)), UserDto.class);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto edit(@PathVariable Long id, @RequestBody UserDto body) {
        return modelMapper.map(service.edit(id, modelMapper.map(body, User.class)), UserDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}