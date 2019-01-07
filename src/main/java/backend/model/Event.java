package backend.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Event extends BaseEntity {

  private Date date;

  @OneToOne
  private Movie movie;

  @ManyToOne
  @JoinTable(name = "event_room")
  private Room room;

  public Event(Date date, Movie movie, Room room) {
    this.date = date;
    this.movie = movie;
    this.room = room;
  }
}
