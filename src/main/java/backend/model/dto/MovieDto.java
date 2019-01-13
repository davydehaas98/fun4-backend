package backend.model.dto;

import java.util.Collection;
import java.util.Date;
import lombok.Data;

@Data
public class MovieDto extends BaseDto {

  private String title;
  private Date releaseDate;
  private String imageUrl;
  private Collection<GenreDto> genres;
}
