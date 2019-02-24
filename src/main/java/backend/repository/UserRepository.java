package backend.repository;

import backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean findUserByIdAndToken(Long id, String token);
}
