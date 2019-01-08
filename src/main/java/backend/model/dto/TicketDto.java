package backend.model.dto;

import lombok.Data;

@Data
public class TicketDto extends BaseDto {

  private EventDto event;

  private SeatDto seat;
}
