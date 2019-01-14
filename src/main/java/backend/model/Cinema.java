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
public class Cinema extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany(
      mappedBy = "cinema",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Collection<Room> rooms;

  public Cinema(String name) {
    this.name = name;
  }
}
