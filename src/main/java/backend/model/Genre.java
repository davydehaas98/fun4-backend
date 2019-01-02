package backend.model;

import backend.model.enumtype.GenreType;
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
  private GenreType name;

  public Genre(GenreType name) {
    this.name = name;
  }
}
