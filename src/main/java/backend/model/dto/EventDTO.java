package backend.model.dto;

import java.util.Date;
import lombok.Data;

@Data
public class EventDTO extends BaseDTO {

    private Date date;
    private MovieDTO movie;
    private RoomDTO room;
}
