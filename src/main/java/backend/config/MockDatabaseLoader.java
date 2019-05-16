package backend.config;

import backend.model.Cinema;
import backend.model.Event;
import backend.model.Genre;
import backend.model.Movie;
import backend.model.Room;
import backend.model.Seat;
import backend.model.User;
import backend.model.enumtype.GenreType;
import backend.model.enumtype.UserRole;
import backend.repository.CinemaRepository;
import backend.repository.EventRepository;
import backend.repository.GenreRepository;
import backend.repository.MovieRepository;
import backend.repository.RoomRepository;
import backend.repository.SeatRepository;
import backend.repository.UserRepository;
import backend.service.interfaces.IAuthService;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

    private final IAuthService authService;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CinemaRepository cinemaRepository;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;

    public MockDatabaseLoader(IAuthService authService, UserRepository userRepository,
        MovieRepository movieRepository,
        GenreRepository genreRepository, CinemaRepository cinemaRepository,
        RoomRepository roomRepository, SeatRepository seatRepository,
        EventRepository eventRepository) {
        this.authService = authService;
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
        createUsers();
        createCinemas();
        createRooms();
        createSeats();
        createGenres();
        createMovies();
        createEvents();
        log.info(movieRepository.findAll().toString());
        log.info(eventRepository.findAll().toString());
        log.info(userRepository.findAll().toString());
    }

    private void createSeats() {
        for (int row = 1; row < 11; row++) {
            for (int number = 1; number < 11; number++) {
                seatRepository.save(new Seat(row, number));
            }
        }
    }

    private void createRooms() {
        Cinema cinema = cinemaRepository.findByName("Vue Eindhoven");
        roomRepository.save(new Room(1, cinema));
        roomRepository.save(new Room(2, cinema));
        roomRepository.save(new Room(3, cinema));
        roomRepository.save(new Room(4, cinema));
    }

    private void createCinemas() {
        cinemaRepository.save(new Cinema("Vue Eindhoven"));
    }

    private void createGenres() {
        for (GenreType genreType : GenreType.values()) {
            genreRepository.save(new Genre(genreType.toString()));
        }
    }

    private void createMovies() {
        createGenres();
        //Set<Genre> genres = genreRepository.findAll();
        Set<Genre> genres = new HashSet<>();
        movieRepository.save(new Movie("Iron Man", new Date(),
            "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
            genres));
        movieRepository.save(new Movie("Wonder Woman", new Date(),
            "https://m.media-amazon.com/images/M/MV5BNDFmZjgyMTEtYTk5MC00NmY0LWJhZjktOWY2MzI5YjkzODNlXkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_SY1000_SX675_AL_.jpg",
            genres));
    }

    private void createEvents() {
        Room room = roomRepository.findAll().get(1);
        eventRepository.save(new Event(new Date(), movieRepository.findByTitle("Iron Man"), room));
    }

    private void createUsers() {
        User user1 = new User("davy", "password", UserRole.ADMIN);
        User user2 = new User("wouter", "password", UserRole.USER);
        User user3 = new User("luc", "password", UserRole.USER);
        User user4 = new User("floris", "password", UserRole.USER);
        authService.register(user1);
        authService.register(user2);
        authService.register(user3);
        authService.register(user4);
    }
}
