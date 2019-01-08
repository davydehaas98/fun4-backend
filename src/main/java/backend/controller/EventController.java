package backend.controller;

import backend.model.dto.CinemaDto;
import backend.model.dto.EventDto;
import backend.service.EventService;
import backend.service.interfaces.ICinemaService;
import backend.service.interfaces.IEventService;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

  private final IEventService service;

  public EventController(EventService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public EventDto findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<EventDto> findAll() {
    return service.findAll();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public EventDto save(@RequestBody EventDto body) {
    return service.save(body);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public EventDto edit(@PathVariable Long id, @RequestBody EventDto body) {
    return service.edit(id, body);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    service.deleteById(id);
  }
}
