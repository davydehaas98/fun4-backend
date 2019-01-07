package backend.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Room extends BaseEntity {

  @Column(unique = true, nullable = false)
  private int number;

  @OneToMany
  private Collection<Seat> seats;

  public Room(int number, Collection<Seat> seats) {
    this.number = number;
    this.seats = seats;
  }
}
