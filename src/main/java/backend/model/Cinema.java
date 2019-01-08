package backend.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cinema extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany
  @JoinTable(name = "cinema_room")
  @OrderBy("number ASC")
  private Collection<Room> rooms;

  public Cinema(String name, Collection<Room> rooms) {
    this.name = name;
    this.rooms = rooms;
  }
}
