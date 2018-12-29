package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Genre {

  @Id
  @GeneratedValue
  private long id;

  @Column(unique = true, nullable = false)
  private String name;

  public Genre(String name) {
    this.name = name;
  }
}
