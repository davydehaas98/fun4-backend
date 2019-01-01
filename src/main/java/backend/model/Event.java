package backend.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Event {

  @Id
  @GeneratedValue
  private long id;

  private Date date;

  @OneToOne
  private Movie movie;

  @OneToOne
  private Room room;

  public Event(Date date, Movie movie, Room room) {
    this.date = date;
    this.movie = movie;
    this.room = room;
  }
}
