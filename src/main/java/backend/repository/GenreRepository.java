package backend.repository;

import backend.model.enumtype.GenreType;
import backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
  Genre findByName(GenreType name);
}
