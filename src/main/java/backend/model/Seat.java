package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Seat {

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false)
  private int row;

  @Column(nullable = false)
  private int number;

  public Seat(int row, int number) {
    this.row = row;
    this.number = number;
  }
}
