package backend.model.dto;

import backend.model.enumtype.GenreType;
import lombok.Data;

@Data
public class GenreDto extends BaseDto {

    private GenreType name;
}
