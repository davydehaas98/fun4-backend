package backend.model;

import backend.model.enumtype.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

  @Enumerated(EnumType.ORDINAL)
  private UserRole userRole;

  @OneToMany
  @JoinTable(name = "user_ticket")
  private Collection<Ticket> tickets;

  public User(String username, String password, UserRole userRole, Collection<Ticket> tickets) {
    this.username = username;
    this.password = password;
    this.tickets = tickets;
    this.userRole = userRole;
  }

  @Override
  public String toString() {
    return String.format("Id: %s, Username: %s, Password: %s", id, username, password);
  }
}
