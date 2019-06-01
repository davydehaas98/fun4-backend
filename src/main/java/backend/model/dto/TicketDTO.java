package backend.model.dto;

import lombok.Data;

@Data
public class TicketDTO extends BaseDTO {

    private EventDTO event;

    private SeatDTO seat;
}
