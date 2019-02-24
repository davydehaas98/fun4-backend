package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
