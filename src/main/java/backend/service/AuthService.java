package backend.service;

import backend.model.User;
import backend.model.dto.RegisterUserDto;
import backend.model.dto.UserDto;
import backend.model.enumtype.UserRole;
import backend.repository.AuthRepository;
import backend.service.interfaces.IAuthService;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

  private final AuthRepository repository;
  private final ModelMapper modelMapper;

  public AuthService(AuthRepository repository, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.repository = repository;
  }

  public UserDto register(RegisterUserDto body) {
    String hashedPassword = BCrypt.hashpw(body.getPassword(), BCrypt.gensalt(10));
    return modelMapper.map(
        repository.save(new User(body.getUsername(), hashedPassword, UserRole.USER, null)),
        UserDto.class
    );
  }

  public UserDto login(RegisterUserDto body) {
    User user = repository.findByUsername(body.getUsername());
    if (user != null && BCrypt.checkpw(body.getPassword(), user.getPassword())) {
      user.setToken(UUID.randomUUID().toString());
      return modelMapper.map(
          repository.save(user),
          UserDto.class
      );
    }
    return null;
  }

  public boolean checkToken(Long id, String token) {
    return repository.findUserByIdAndToken(id, token);
  }
}
