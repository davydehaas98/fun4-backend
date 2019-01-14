package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Room extends BaseEntity {

  @Column(unique = true, nullable = false)
  private int number;

//  @ManyToMany
//  @JoinTable(name = "room_seat")
//  private Collection<Seat> seats;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cinema_id")
  private Cinema cinema;

  public Room(int number, Cinema cinema) {
    this.number = number;
    this.cinema = cinema;
  }
}
