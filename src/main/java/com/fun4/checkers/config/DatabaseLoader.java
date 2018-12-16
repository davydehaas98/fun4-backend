package com.fun4.checkers.config;

import com.fun4.checkers.model.User;
import com.fun4.checkers.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatabaseLoader implements CommandLineRunner {

  private final UserRepository userRepository;

  @Autowired
  public DatabaseLoader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) {
    log.info("Fill database with mock data");
    userRepository.save(new User("davy", "abcdefg"));
    userRepository.save(new User("floris", "abcdefg"));
    log.info(userRepository.findAll().toString());
  }
}
