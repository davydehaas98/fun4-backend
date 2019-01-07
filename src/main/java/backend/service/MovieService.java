package backend.service;

import backend.model.dto.MovieDto;
import backend.model.dto.UserDto;
import backend.repository.MovieRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  private final MovieRepository movieRepository;
  private final ModelMapper modelMapper;

  public MovieService(MovieRepository movieRepository, ModelMapper modelMapper) {
    this.movieRepository = movieRepository;
    this.modelMapper = modelMapper;
  }

  public Collection<MovieDto> findAll() {
    return movieRepository.findAll()
        .stream()
        .map(item -> modelMapper.map(
            item,
            MovieDto.class
        )).collect(Collectors.toList());
  }

  public MovieDto findById(Long id) {
    return modelMapper.map(
        movieRepository.findById(id),
        MovieDto.class
    );
  }

  public MovieDto save(MovieDto body) {
    return null;
//    return modelMapper.map(
//        movieRepository.save(new MovieDto(body.getUsername(), body.getPassword(), body.getUserRole(),
//            body.getTickets())),
//        MovieDto.class
//    );
  }

  public MovieDto edit(Long id, UserDto body) {
    return null;
//    return modelMapper.map(
//        movieRepository.findById(id)
//            .map(item -> {
//              item.setUsername(body.getUsername());
//              item.setPassword(body.getUsername());
//              return item;
//            }),
//        MovieDto.class);
  }

  public void deleteById(Long id) {
    movieRepository.deleteById(id);
  }
}
