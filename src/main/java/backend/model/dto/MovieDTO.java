package backend.model.dto;

import java.util.Date;
import java.util.Set;
import lombok.Data;

@Data
public class MovieDTO extends BaseDTO {

    private String title;
    private Date releaseDate;
    private String imageUrl;
    private Set<GenreDTO> genres;
}
