package backend.model.dto;

import java.util.Date;
import lombok.Data;

@Data
public class EventDto extends BaseDto {

  private Date date;
  private MovieDto movie;
  private RoomDto room;
}
