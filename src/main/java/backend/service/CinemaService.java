package backend.service;

import backend.model.dto.CinemaDto;
import backend.repository.CinemaRepository;
import backend.service.interfaces.ICinemaService;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CinemaService implements ICinemaService {

  private final CinemaRepository repository;
  private final ModelMapper modelMapper;

  public CinemaService(CinemaRepository repository, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.repository = repository;
  }

  public CinemaDto findById(Long id) {
    Optional object = repository.findById(id);
    if (object.isPresent()) {
      return modelMapper.map(
          object.get(),
          CinemaDto.class
      );
    }
    return null;
  }

  public Collection<CinemaDto> findAll() {
    return repository.findAll().stream()
        .map(item ->
            modelMapper.map(
                item,
                CinemaDto.class
            ))
        .collect(Collectors.toList());
  }

  public CinemaDto save(CinemaDto body) {
    return null;
  }

  public CinemaDto edit(Long id, CinemaDto body) {
    return null;
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
