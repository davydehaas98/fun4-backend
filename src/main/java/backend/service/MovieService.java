package backend.service;

import backend.model.Movie;
import backend.repository.MovieRepository;
import backend.service.interfaces.IMovieService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Collection<Movie> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public Movie save(Movie body) {
        return repository.save(body);
//        Set<Genre> set = body.getGenres().stream()
//            .map(item ->
//                modelMapper.map(
//                    item,
//                    Genre.class
//                ))
//            .collect(Collectors.toSet());
//
//        return modelMapper.map(
//            repository
//                .save(new Movie(body.getTitle(), body.getReleaseDate(), body.getImageUrl(), set)),
//            MovieDto.class
//        );
    }

    public Movie edit(Long id, Movie body) {

        Movie item = repository.findById(id).orElseThrow();
        item.setTitle(body.getTitle());
        item.setReleaseDate(body.getReleaseDate());
        item.setImageUrl(body.getImageUrl());
        item.setGenres(body.getGenres());
        return repository.save(item);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
