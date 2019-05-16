package backend.controller;

import backend.model.Movie;
import backend.model.dto.MovieDto;
import backend.service.MovieService;
import backend.service.interfaces.IMovieService;
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
@RequestMapping("/movies")
public class MovieController {

    private final ModelMapper modelMapper;
    private final IMovieService service;

    public MovieController(ModelMapper modelMapper, MovieService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto findById(@PathVariable Long id) {
        return modelMapper.map(service.findById(id), MovieDto.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MovieDto> findAll() {
        return service.findAll()
            .stream().map(item ->
                modelMapper.map(item, MovieDto.class)
            ).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto save(@RequestBody MovieDto body) {
        return modelMapper.map(service.save(modelMapper.map(body, Movie.class)), MovieDto.class);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto edit(@PathVariable Long id, @RequestBody MovieDto body) {
        return modelMapper
            .map(service.edit(id, modelMapper.map(body, Movie.class)), MovieDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
