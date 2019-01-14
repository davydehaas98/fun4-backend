package backend.model;

import backend.model.enumtype.GenreType;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Genre extends BaseEntity {

  @Column
  private String name;

//  @ManyToMany(fetch = FetchType.LAZY,
//  cascade = {
//      CascadeType.PERSIST,
//      CascadeType.MERGE
//  })
//  private Set<Movie> movies = new HashSet<>();

  public Genre(String name) {
    this.name = name;
  }
}
