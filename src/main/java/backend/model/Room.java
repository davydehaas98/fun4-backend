package backend.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Room {

  @Id
  @GeneratedValue
  private long id;

  @Column(unique = true, nullable = false)
  private int number;

  @OneToMany
  @JoinTable(name = "room_seat")
  private Collection<Seat> seats;

  public Room(int number, Collection<Seat> seats) {
    this.number = number;
    this.seats = seats;
  }
}
