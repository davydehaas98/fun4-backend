package backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Profile({"production"})
public class MySQLDatabaseConfig {
  @Value("${app.datasource.driverClassName}")
  private String driverClassName;
  @Value("${app.datasource.url}")
  private String url;
  @Value("${app.datasource.username}")
  private String username;
  @Value("${app.datasource.password}")
  private String password;
}
