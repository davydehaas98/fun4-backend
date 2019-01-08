package backend.service.interfaces;

import backend.model.dto.UserDto;

public interface IUserService extends IBaseService<UserDto> {

  UserDto findByUsername(String username);
}
