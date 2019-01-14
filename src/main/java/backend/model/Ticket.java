package backend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ticket extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  private Event event;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seat_id")
  private Seat seat;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public Ticket(Event event, Seat seat, User user) {
    this.event = event;
    this.seat = seat;
    this.user = user;
  }
}
