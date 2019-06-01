package backend.model.dto;

import java.util.Collection;
import lombok.Data;

@Data
public class CinemaDTO extends BaseDTO {

    private String name;
    private Collection<RoomDTO> rooms;
}
