package backend.config;

import backend.model.Event;
import backend.model.enumtype.UserRole;
import backend.model.enumtype.GenreType;
import backend.model.Cinema;
import backend.model.Genre;
import backend.model.Movie;
import backend.model.Room;
import backend.model.Seat;
import backend.model.User;
import backend.repository.CinemaRepository;
import backend.repository.EventRepository;
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
  private final EventRepository eventRepository;

  public MockDatabaseLoader(UserRepository userRepository, MovieRepository movieRepository,
      GenreRepository genreRepository, CinemaRepository cinemaRepository,
      RoomRepository roomRepository, SeatRepository seatRepository, EventRepository eventRepository) {
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.genreRepository = genreRepository;
    this.cinemaRepository = cinemaRepository;
    this.roomRepository = roomRepository;
    this.seatRepository = seatRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public void run(String... args) {
    loadDatabase();
  }

  private void loadDatabase() {
    log.info("Fill database with mock data");
    createCinemas();
    createMovies();
    createEvents();
    createUsers();
    log.info(movieRepository.findAll().toString());
    log.info(eventRepository.findAll().toString());
    log.info(userRepository.findAll().toString());
  }

  private void createSeats() {
    for (int row = 1; row < 8; row++) {
      for (int number = 1; number < 11; number++) {
        seatRepository.save(new Seat(row, number));
      }
    }
  }

  private void createRooms() {
    createSeats();
    Collection<Seat> seats = seatRepository.findAll();
    roomRepository.save(new Room(1, seats));
    roomRepository.save(new Room(2, seats));
    roomRepository.save(new Room(3, seats));
    roomRepository.save(new Room(4, seats));
  }

  private void createCinemas() {
    createRooms();
    cinemaRepository.save(new Cinema("testCinema", roomRepository.findAll()));
  }

  private void createGenres() {
    for (GenreType genreType : GenreType.values()) {
      genreRepository.save(new Genre(genreType));
    }
  }

  private void createMovies() {
    createGenres();
    Collection<Genre> genres = new ArrayList<>();
    genres.add(genreRepository.findByName(GenreType.Action));
    genres.add(genreRepository.findByName(GenreType.Thriller));
    genres.add(genreRepository.findByName(GenreType.Comedy));
    movieRepository.save(new Movie("testMovie1", new Date(), genres));
    movieRepository.save(new Movie("testMovie2", new Date(), genres));
  }

  private void createEvents() {
    Room room = (Room)cinemaRepository.findByName("testCinema").getRooms().toArray()[1];
    eventRepository.save(new Event(new Date(), movieRepository.findByTitle("test1"), room));
  }

  private void createUsers() {
    userRepository.save(new User("davy", "davy", UserRole.USER, null));
    userRepository.save(new User("admin", "admin", UserRole.ADMIN, null));
  }
}
