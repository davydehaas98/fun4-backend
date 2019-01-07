package backend.model.dto;

import backend.model.Genre;
import java.util.Collection;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDto {

  private long id;
  private String title;
  private Date releaseDate;
  private Collection<Genre> genres;

  public MovieDto(long id, String title, Date releaseDate, Collection<Genre> genres) {
    this.id = id;
    this.title = title;
    this.releaseDate = releaseDate;
    this.genres = genres;
  }
}
