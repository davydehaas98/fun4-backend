package backend.repository;

import backend.model.Genre;
import backend.model.enumtype.GenreType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

  Genre findByName(String name);
}
