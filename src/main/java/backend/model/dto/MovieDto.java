package backend.model.dto;

import java.util.Date;
import java.util.Set;
import lombok.Data;

@Data
public class MovieDto extends BaseDto {

    private String title;
    private Date releaseDate;
    private String imageUrl;
    private Set<GenreDto> genres;
}
