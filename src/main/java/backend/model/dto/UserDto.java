package backend.model.dto;

import backend.model.Ticket;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String username;
  private String password;
  private Collection<Ticket> tickets;

  public UserDto(String username, String password, Collection<Ticket> tickets) {
    this.username = username;
    this.password = password;
    this.tickets = tickets;
  }
}
