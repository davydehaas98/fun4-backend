package backend.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Seat extends BaseEntity {

    @Column(nullable = false, name = "`row`")
    private int row;

    @Column(nullable = false)
    private int number;

    @OneToMany(
        mappedBy = "seat",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Collection<Ticket> tickets;

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
    }
}
