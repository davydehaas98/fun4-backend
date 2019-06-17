package backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import backend.model.User;
import backend.model.enumtype.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void findAll_ReturnAll() {
        Collection<User> collection = new ArrayList<>();
        collection.add(repository.save(new User("user1", "password1", UserRole.USER)));
        collection.add(repository.save(new User("user2", "password1", UserRole.ADMIN)));

        assertEquals(collection, repository.findAll());
    }
}
