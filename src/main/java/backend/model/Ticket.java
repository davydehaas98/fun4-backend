package backend.model;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ticket extends BaseEntity {

  @ManyToOne
  private Event event;

  @OneToOne
  private Seat seat;

  public Ticket(Event event, Seat seat) {
    this.event = event;
    this.seat = seat;
  }
}
