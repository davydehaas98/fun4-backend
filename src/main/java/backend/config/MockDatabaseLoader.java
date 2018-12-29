package backend.config;

import backend.model.User;
import backend.repository.UserRepository;
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
  private final EntityManager entityManager;

  public MockDatabaseLoader(UserRepository userRepository, EntityManager entityManager) {
    this.userRepository = userRepository;
    this.entityManager = entityManager;
  }

  @Override
  public void run(String... args) {
    loadDatabase();
  }

  private void loadDatabase() {
    log.info("Fill database with mock data");
    userRepository.save(new User("davy", "abcdefg"));
    userRepository.save(new User("steen", "abcdefg"));
    entityManager.flush();
    log.info(userRepository.findAll().toString());
  }
}
