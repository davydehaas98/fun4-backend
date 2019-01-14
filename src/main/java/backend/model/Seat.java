package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Seat extends BaseEntity {

  @Column(nullable = false, name = "`row`")
  private int row;

  @Column(nullable = false)
  private int number;

  public Seat(int row, int number) {
    this.row = row;
    this.number = number;
  }
}
