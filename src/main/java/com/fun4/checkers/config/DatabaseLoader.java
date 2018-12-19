package com.fun4.checkers.config;

import com.fun4.checkers.model.User;
import com.fun4.checkers.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatabaseLoader implements CommandLineRunner {

  private final UserRepository userRepository;

  public DatabaseLoader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) {
    loadDatabase();
  }

  private void loadDatabase() {
    log.info("Fill database with mock data");
    userRepository.save(new User("davy", "abcdefg"));
    userRepository.save(new User("steen", "abcdefg"));
    log.info(userRepository.findAll().toString());
  }
}
