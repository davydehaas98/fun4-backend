package backend.service.interfaces;

import backend.model.dto.UserDto;

public interface IUserService extends IService<UserDto> {

  UserDto findByUsername(String username);
}
