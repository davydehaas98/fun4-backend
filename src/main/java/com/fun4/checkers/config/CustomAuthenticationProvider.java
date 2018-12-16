package com.fun4.checkers.config;

import com.fun4.checkers.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserRepository userRepository;

  CustomAuthenticationProvider(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    var user = userRepository.findByUsername(username);
    if (user.isPresent() && user.get().getPassword().equals(password)) {
      return new UsernamePasswordAuthenticationToken(username, password);
    } else {
      throw new BadCredentialsException("Authentication failed");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
