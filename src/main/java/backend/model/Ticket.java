package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ticket {

  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  private Event event;

  @OneToOne
  private Seat seat;

  public Ticket(Event event, Seat seat) {
    this.event = event;
    this.seat = seat;
  }
}
