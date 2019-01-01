package backend.config;

import backend.model.Cinema;
import backend.model.Genre;
import backend.model.Movie;
import backend.model.Room;
import backend.model.Seat;
import backend.model.User;
import backend.repository.CinemaRepository;
import backend.repository.GenreRepository;
import backend.repository.MovieRepository;
import backend.repository.RoomRepository;
import backend.repository.SeatRepository;
import backend.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
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
  private final SeatRepository seatRepository;

  public MockDatabaseLoader(UserRepository userRepository, MovieRepository movieRepository,
      GenreRepository genreRepository, CinemaRepository cinemaRepository,
      RoomRepository roomRepository, SeatRepository seatRepository) {
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.genreRepository = genreRepository;
    this.cinemaRepository = cinemaRepository;
    this.roomRepository = roomRepository;
    this.seatRepository = seatRepository;
  }

  @Override
  public void run(String... args) {
    loadDatabase();
  }

  private void loadDatabase() {
    log.info("Fill database with mock data");
    createCinemas();
    createMovies();
    createUsers();
    log.info(cinemaRepository.findAll().toString());
    log.info(movieRepository.findAll().toString());
    log.info(userRepository.findAll().toString());
  }

  private Collection<Seat> createSeats() {
    Collection<Seat> seats = new ArrayList<>();
    for (int row = 1; row < 8; row++) {
      for (int number = 1; number < 11; number++) {
        seats.add(seatRepository.save(new Seat(row, number)));
      }
    }
    return seats;
  }

  private void createRooms() {
    Collection<Seat> seats = createSeats();
    roomRepository.save(new Room(1, seats));
    roomRepository.save(new Room(2, seats));
    roomRepository.save(new Room(3, seats));
    roomRepository.save(new Room(4, seats));
  }

  private void createCinemas() {
    createRooms();
    cinemaRepository.save(new Cinema("Bioscoop", roomRepository.findAll()));
  }

  private void createUsers() {
    userRepository.save(new User("davy", "davy", null));
    userRepository.save(new User("admin", "admin", null));
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
