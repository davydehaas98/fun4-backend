package backend.controller;

import backend.model.User;
import backend.model.dto.RegisterUserDto;
import backend.model.dto.UserDto;
import backend.service.interfaces.IAuthService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ModelMapper modelMapper;
    private final IAuthService service;

    public AuthController(ModelMapper modelMapper, IAuthService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto register(@RequestBody RegisterUserDto body) {
        return modelMapper.map(
            service.register(modelMapper.map(body, User.class)),
            UserDto.class
        );
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody RegisterUserDto body) {
        return modelMapper.map(
            service.login(modelMapper.map(body, User.class)),
            UserDto.class
        );
    }

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody UserDto body) {
        return modelMapper.map(
            service.checkToken(modelMapper.map(body, User.class)),
            UserDto.class
        );
    }
}
