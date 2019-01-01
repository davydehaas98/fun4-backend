package backend.config;

import backend.model.Cinema;
import backend.model.Genre;
import backend.model.Movie;
import backend.model.Room;
import backend.model.User;
import backend.repository.CinemaRepository;
import backend.repository.GenreRepository;
import backend.repository.MovieRepository;
import backend.repository.RoomRepository;
import backend.repository.UserRepository;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Component
@Profile({"development"})
public class MockDatabaseLoader implements CommandLineRunner {

  private final UserRepository userRepository;
  private final MovieRepository movieRepository;
  private final GenreRepository genreRepository;
  private final CinemaRepository cinemaRepository;
  private final RoomRepository roomRepository;

  public MockDatabaseLoader(UserRepository userRepository, MovieRepository movieRepository,
      GenreRepository genreRepository, CinemaRepository cinemaRepository,
      RoomRepository roomRepository) {
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.genreRepository = genreRepository;
    this.cinemaRepository = cinemaRepository;
    this.roomRepository = roomRepository;
  }

  @Override
  public void run(String... args) {
    loadDatabase();
  }

  private void loadDatabase() {
    log.info("Fill database with mock data");
    createUsers();
    createCinemas();
    createMovies();
    log.info(userRepository.findAll().toString());
    log.info(movieRepository.findAll().toString());
    log.info(cinemaRepository.findAll().toString());
  }

  private void createUsers() {
    userRepository.save(new User("davy", "davy", null));
    userRepository.save(new User("admin", "admin", null));
  }

  private void createCinemas() {
    createRooms();
    cinemaRepository.save(new Cinema("Bioscoop", roomRepository.findAll()));
  }

  private void createRooms() {
    roomRepository.save(new Room(1, null));
    roomRepository.save(new Room(2, null));
    roomRepository.save(new Room(3, null));
    roomRepository.save(new Room(4, null));
  }

  private void createGenres() {
    genreRepository.save(new Genre("Action"));
    genreRepository.save(new Genre("Adventure"));
    genreRepository.save(new Genre("Comedy"));
  }

  private void createMovies() {
    createGenres();
    movieRepository.save(new Movie("name", new Date(), genreRepository.findAll()));
    movieRepository.save(new Movie("name2", new Date(), genreRepository.findAll()));
  }
}
