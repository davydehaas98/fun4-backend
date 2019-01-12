package backend.service;

import backend.model.Ticket;
import backend.model.User;
import backend.model.dto.UserDto;
import backend.repository.UserRepository;
import backend.service.interfaces.IUserService;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  private final UserRepository repository;
  private final ModelMapper modelMapper;

  public UserService(UserRepository repository, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.repository = repository;
  }

  public UserDto findById(Long id) {
    if (repository.findById(id).isPresent()) {
      return modelMapper.map(
          repository.findById(id).get(),
          UserDto.class
      );
    }
    return null;
  }

  public Collection<UserDto> findAll() {
    return repository.findAll().stream()
        .map(item ->
            modelMapper.map(
                item,
                UserDto.class
            )).collect(Collectors.toList());
  }

  public UserDto save(UserDto body) {
    return null;
  }

  public UserDto edit(Long id, UserDto body) {
    return modelMapper.map(
        repository.findById(id)
            .map(item -> {
              item.setUsername(body.getUsername());
              item.setPassword(body.getUsername());
              return item;
            }),
        UserDto.class);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
