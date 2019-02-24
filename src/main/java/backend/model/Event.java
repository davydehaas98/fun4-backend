package backend.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

    @OneToOne
    private Room room;

    @OneToMany(
        mappedBy = "event",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Collection<Ticket> tickets;

    public Event(Date date, Movie movie, Room room) {
        this.date = date;
        this.movie = movie;
        this.room = room;
    }
}
