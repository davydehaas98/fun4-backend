package backend.controller;

import backend.model.Event;
import backend.model.dto.EventDto;
import backend.service.EventService;
import backend.service.interfaces.IEventService;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;
    private final IEventService service;

    public EventController(ModelMapper modelMapper, EventService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDto findById(@PathVariable Long id) {
        return modelMapper.map(service.findById(id), EventDto.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<EventDto> findAll() {
        return service.findAll()
            .stream().map(item ->
                modelMapper.map(item, EventDto.class)
            ).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDto save(@RequestBody EventDto body) {
        return modelMapper.map(service.save(modelMapper.map(body, Event.class)), EventDto.class);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDto edit(@PathVariable Long id, @RequestBody EventDto body) {
        return modelMapper
            .map(service.edit(id, modelMapper.map(body, Event.class)), EventDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
