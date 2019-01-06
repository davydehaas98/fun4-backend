package backend.model.dto;

import backend.model.Ticket;
import backend.model.enumtype.UserRole;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String username;
  private String password;
  private UserRole userRole;
  private Collection<Ticket> tickets;

  public UserDto(String username, String password, UserRole userRole, Collection<Ticket> tickets) {
    this.username = username;
    this.password = password;
    this.userRole = userRole;
    this.tickets = tickets;
  }
}
