package backend.config;

import backend.model.User;
import backend.repository.UserRepository;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
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
    Object credentials = authentication.getCredentials();
    if (!(credentials instanceof String)) {
      return null;
    }
    String password = credentials.toString();
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new BadCredentialsException("Authentication failed for " + username);
    }
    var authorities = new ArrayList<GrantedAuthority>();
    authorities.add(user.getUserRole());
    return new UsernamePasswordAuthenticationToken(username, password, authorities);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
