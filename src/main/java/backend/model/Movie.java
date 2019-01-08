package backend.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Movie extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String title;

  private Date releaseDate;

  @ManyToMany
  @JoinTable(name = "movie_genre")
  @OrderBy("name ASC")
  private Collection<Genre> genres;

  public Movie(String title, Date releaseDate, Collection<Genre> genres) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.genres = genres;
  }
}
