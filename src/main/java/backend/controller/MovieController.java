package backend.controller;

import backend.model.Movie;
import backend.model.dto.GenreDTO;
import backend.model.dto.MovieDTO;
import backend.service.MovieService;
import backend.service.interfaces.IMovieService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public MovieDTO findById(@PathVariable Long id) {
        return modelMapper.map(
            service.findById(id),
            MovieDTO.class
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MovieDTO> findAll() {
        return service.findAll().stream().map(item ->
            modelMapper.map(
                item,
                MovieDTO.class
            )
        ).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MovieDTO save(@RequestBody MovieDTO body) {
        return modelMapper.map(
            service.save(modelMapper.map(body, Movie.class)),
            MovieDTO.class
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDTO edit(@PathVariable Long id, @RequestBody MovieDTO body) {
        return modelMapper.map(
            service.edit(id, modelMapper.map(body, Movie.class)),
            MovieDTO.class
        );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
