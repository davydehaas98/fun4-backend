package backend.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import backend.model.User;
import backend.model.enumtype.UserRole;
import backend.repository.UserRepository;
import com.mysql.cj.log.Log;
import java.io.Console;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repositoryMock;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveUser_returnsNewUser() {
        when(repositoryMock.save(any(User.class))).thenReturn(new User());

        User user = new User("username", "password", UserRole.USER);

//        assertEquals(user, service.save(user));
        assertEquals(2, service.findAll().size());
        assertNotEquals(user, service.save(user));
    }
}
