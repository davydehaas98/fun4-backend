package backend.model.dto;

import java.util.Collection;
import lombok.Data;

@Data
public class CinemaDto extends BaseDto {

  private String name;
  private Collection<RoomDto> rooms;
}
