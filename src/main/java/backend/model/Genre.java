package backend.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

  @ManyToMany
  private Collection<Movie> movies;

  public Genre(String name) {
    this.name = name;
  }
}
