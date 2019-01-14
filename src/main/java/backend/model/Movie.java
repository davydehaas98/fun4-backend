package backend.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Movie extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String title;

  private Date releaseDate;

  private String imageUrl;

  @ManyToMany
  @JoinTable(name = "movie_genre",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private Set<Genre> genres;

  public Movie(String title, Date releaseDate, String imageUrl, Set<Genre> genres) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.imageUrl = imageUrl;
    this.genres = genres;
  }
}
