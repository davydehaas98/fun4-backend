package backend.controller;

import backend.model.Movie;
import backend.repository.MovieRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

  private final MovieRepository movieRepository;
  private final ModelMapper modelMapper;

  public MovieController(MovieRepository movieRepository, ModelMapper modelMapper) {
    this.movieRepository = movieRepository;
    this.modelMapper = modelMapper;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<Movie> GetMovies() {
    return movieRepository.findAll()
        .stream().map(movie -> modelMapper.map(movie, Movie.class))
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Movie getMovieById(@PathVariable Long movieId) {
    return modelMapper.map(
        movieRepository.findById(movieId),
        Movie.class
    );
  }
}
