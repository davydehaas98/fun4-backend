package backend.service;

import backend.model.User;
import backend.model.dto.UserDto;
import backend.repository.UserRepository;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceBase<UserDto> {

  public UserService(UserRepository repository, ModelMapper modelMapper) {
    super(repository, UserDto.class, modelMapper);
  }

  public UserDto save(UserDto body) {
    return modelMapper.map(
        repository.save(new User(body.getUsername(), body.getPassword(), body.getUserRole(),
            body.getTickets())),
        dtoClass
    );
  }

  public UserDto edit(Long id, UserDto body) {
    return modelMapper.map(
        ((UserRepository)repository).findById(id)
            .map(item -> {
              item.setUsername(body.getUsername());
              item.setPassword(body.getUsername());
              return item;
            }),
        dtoClass);
  }
}
