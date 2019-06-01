package backend.model.dto;

import backend.model.enumtype.UserRole;
import java.util.Collection;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO {

    private String username;
    //private String password;
    private String token;
    private UserRole userRole;
    private Collection<TicketDTO> tickets;
}
