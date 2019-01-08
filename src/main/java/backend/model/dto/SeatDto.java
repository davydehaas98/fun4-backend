package backend.model.dto;

import lombok.Data;

@Data
public class SeatDto extends BaseDto {

  private int row;
  private int number;
}
