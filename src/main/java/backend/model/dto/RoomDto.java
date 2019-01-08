package backend.model.dto;

import java.util.Collection;
import lombok.Data;

@Data
public class RoomDto extends BaseDto {

  private int number;
  private Collection<SeatDto> seats;
}
