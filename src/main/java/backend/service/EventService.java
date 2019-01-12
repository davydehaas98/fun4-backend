package backend.service;

import backend.model.dto.EventDto;
import backend.repository.EventRepository;
import backend.service.interfaces.IEventService;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

  private final EventRepository repository;
  private final ModelMapper modelMapper;

  public EventService(EventRepository repository, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.repository = repository;
  }

  public EventDto findById(Long id) {
    if (repository.findById(id).isPresent()) {
      return modelMapper.map(
          repository.findById(id).get(),
          EventDto.class
      );
    }
    return null;
  }

  public Collection<EventDto> findAll() {
    return repository.findAll().stream()
        .map(item ->
            modelMapper.map(
                item,
                EventDto.class
            ))
        .collect(Collectors.toList());
  }

  public EventDto save(EventDto body) {
    return null;
  }

  public EventDto edit(Long id, EventDto body) {
    return null;
  }

  public void deleteById(Long id) {

  }
}
