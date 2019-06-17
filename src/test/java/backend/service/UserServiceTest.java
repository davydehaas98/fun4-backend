package backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import backend.model.User;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    @Mock
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById_returnsUser() {
        // Arrange
        User user = mock(User.class);

        // Act
        when(service.findById(1L)).thenReturn(user);

        // Assert
        assertEquals(user, service.findById(1L));
    }

    @Test
    void testFindAll_returnsAllUsers() {
        @SuppressWarnings( "unchecked" )
        ArrayList<User> users = mock(ArrayList.class);

        when(service.findAll()).thenReturn(users);

        assertEquals(service.findAll(), users);
    }


    @Test
    void testSave_returnsSavedUser() {
        User user = mock(User.class);

        when(service.save(user)).thenReturn(user);

        assertEquals(service.save(user), user);
    }
}
