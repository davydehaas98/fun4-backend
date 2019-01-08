package backend.model.dto;

import backend.model.enumtype.UserRole;
import java.util.Collection;
import lombok.Data;

@Data
public class UserDto extends BaseDto {

  private String username;
  private String password;
  private UserRole userRole;
  private Collection<TicketDto> tickets;
}
