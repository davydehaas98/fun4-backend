package backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue
  private long id;

  @Column(unique = true, nullable = false)
  private String username;

  @JsonIgnore
  private String password;

  @OneToMany
  @JoinTable(name = "user_ticket")
  private Collection<Ticket> tickets;

  public User(String username, String password, Collection<Ticket> tickets) {
    this.username = username;
    this.password = password;
    this.tickets = tickets;
  }

  @Override
  public String toString() {
    return String.format("Id: %s, Username: %s, Password: %s", id, username, password);
  }
}
