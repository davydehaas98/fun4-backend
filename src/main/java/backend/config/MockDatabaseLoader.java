package backend.config;

import backend.model.Genre;
import backend.model.Movie;
import backend.model.User;
import backend.repository.GenreRepository;
import backend.repository.MovieRepository;
import backend.repository.UserRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
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
  private final EntityManager entityManager;

  public MockDatabaseLoader(UserRepository userRepository, MovieRepository movieRepository, GenreRepository genreRepository, EntityManager entityManager) {
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.genreRepository = genreRepository;
    this.entityManager = entityManager;
  }

  @Override
  public void run(String... args) {
    loadDatabase();
  }

  private void loadDatabase() {
    log.info("Fill database with mock data");
    createUsers();
    createMovies();
    log.info(userRepository.findAll().toString());
    log.info(movieRepository.findAll().toString());
  }

  private void createUsers() {
    userRepository.save(new User("davy", "abcdefg"));
    userRepository.save(new User("steen", "abcdefg"));
  }

  private void createMovies() {
    Collection<Genre> genres = new ArrayList<>();
    genres.add(genreRepository.save(new Genre("Action")));
    genres.add(genreRepository.save(new Genre("Adventure")));
    genres.add(genreRepository.save(new Genre("Comedy")));
    movieRepository.save(new Movie("name", new Date(), genres));
  }
}
