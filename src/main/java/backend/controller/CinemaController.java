package backend.controller;

import backend.model.Cinema;
import backend.model.dto.CinemaDto;
import backend.service.CinemaService;
import backend.service.interfaces.ICinemaService;
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
@RequestMapping("/cinemas")
public class CinemaController {

    private final ModelMapper modelMapper;
    private final ICinemaService service;

    public CinemaController(ModelMapper modelMapper, CinemaService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CinemaDto findById(@PathVariable Long id) {
        return modelMapper.map(service.findById(id), CinemaDto.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CinemaDto> findAll() {
        return service.findAll()
            .stream().map(item ->
                modelMapper.map(item, CinemaDto.class)
            ).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CinemaDto save(@RequestBody CinemaDto body) {
        return modelMapper.map(service.save(modelMapper.map(body, Cinema.class)), CinemaDto.class);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CinemaDto edit(@PathVariable Long id, @RequestBody CinemaDto body) {
        return modelMapper
            .map(service.edit(id, modelMapper.map(body, Cinema.class)), CinemaDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
