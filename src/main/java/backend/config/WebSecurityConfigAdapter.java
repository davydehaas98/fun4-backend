package backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

  private final CustomAuthenticationProvider customAuthenticationProvider;

  public WebSecurityConfigAdapter(CustomAuthenticationProvider customAuthenticationProvider) {
    this.customAuthenticationProvider = customAuthenticationProvider;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(customAuthenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
//    http.cors()
//        .and()
//        .authorizeRequests()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .httpBasic();
  }
}
