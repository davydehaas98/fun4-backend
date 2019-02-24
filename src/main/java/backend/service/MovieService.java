package backend.service;

import backend.model.Genre;
import backend.model.Movie;
import backend.model.dto.MovieDto;
import backend.repository.MovieRepository;
import backend.service.interfaces.IMovieService;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository repository;
    private final ModelMapper modelMapper;

    public MovieService(MovieRepository repository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public MovieDto findById(Long id) {
        Optional object = repository.findById(id);
        if (object.isPresent()) {
            return modelMapper.map(
                object.get(),
                MovieDto.class
            );
        }
        return null;
    }

    public Collection<MovieDto> findAll() {
        return repository.findAll().stream()
            .map(item ->
                modelMapper.map(
                    item,
                    MovieDto.class
                ))
            .collect(Collectors.toList());
    }

    public MovieDto save(MovieDto body) {
        Set<Genre> set = body.getGenres().stream()
            .map(item ->
                modelMapper.map(
                    item,
                    Genre.class
                ))
            .collect(Collectors.toSet());

        return modelMapper.map(
            repository
                .save(new Movie(body.getTitle(), body.getReleaseDate(), body.getImageUrl(), set)),
            MovieDto.class
        );
    }

    public MovieDto edit(Long id, MovieDto body) {
        Set<Genre> set = body.getGenres().stream()
            .map(item ->
                modelMapper.map(
                    item,
                    Genre.class
                ))
            .collect(Collectors.toSet());

        return modelMapper.map(
            repository.findById(id)
                .map(item -> {
                    item.setTitle(body.getTitle());
                    item.setReleaseDate(body.getReleaseDate());
                    item.setImageUrl(body.getImageUrl());
                    item.setGenres(set);
                    return item;
                }),
            MovieDto.class
        );
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
