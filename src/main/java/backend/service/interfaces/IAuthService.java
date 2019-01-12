package backend.service.interfaces;

import backend.model.dto.RegisterUserDto;
import backend.model.dto.UserDto;

public interface IAuthService {

  UserDto register(RegisterUserDto body);

  UserDto login(RegisterUserDto body);
}
