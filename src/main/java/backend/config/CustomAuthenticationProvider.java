package backend.config;

import backend.model.User;
import backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final UserRepository userRepository;

  public CustomAuthenticationProvider(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    User user = (User)userRepository.findAll().toArray()[0];
    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
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
