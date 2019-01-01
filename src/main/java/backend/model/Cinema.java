package backend.model;

import java.util.ArrayList;
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
public class Cinema {

  @Id
  @GeneratedValue
  private long id;

  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany
  @JoinTable(name = "cinema_room")
  private Collection<Room> rooms;

  public Cinema(String name, Collection<Room> rooms) {
    this.name = name;
    this.rooms = rooms;
  }
}
