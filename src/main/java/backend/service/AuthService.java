package backend.service;

import backend.model.User;
import backend.model.dto.RegisterUserDto;
import backend.model.dto.UserDto;
import backend.model.enumtype.UserRole;
import backend.repository.AuthRepository;
import backend.service.interfaces.IAuthService;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

  private final AuthRepository repository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  public AuthService(AuthRepository repository, ModelMapper modelMapper,
      PasswordEncoder passwordEncoder) {
    this.modelMapper = modelMapper;
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto register(RegisterUserDto body) {
    String encodedPassword = passwordEncoder.encode(body.getPassword());
    return modelMapper.map(
        repository.save(new User(body.getUsername(), encodedPassword, UserRole.USER, null)),
        UserDto.class
    );
  }

  public UserDto login(RegisterUserDto body) {
    User user = repository.findByUsername(body.getUsername());
    if (user != null && passwordEncoder.matches(user.getPassword(), body.getPassword())) {
      user.setToken(UUID.randomUUID().toString());
      return modelMapper.map(
          user,
          UserDto.class
      );
    }
    return null;
  }

  public boolean checkToken(Long id, String token) {
    return repository.findUserByIdAndToken(id, token);
  }
}
