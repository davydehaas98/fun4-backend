package backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import backend.model.enumtype.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;

    @BeforeEach
    void setUp() {
        user1 = new User("test1", "password1", UserRole.USER);
        user2 = new User("test1", "password1", UserRole.USER);
        user3 = new User("test2", "password1", UserRole.USER);
        user4 = new User("test1", "password2", UserRole.USER);
        user5 = new User("test1", "password1", UserRole.ADMIN);
        user6 = new User("test2", "password2", UserRole.ADMIN);
    }

    @Test
    void constructorTest() {
        assertEquals("test1", user1.getUsername());
        assertEquals("password1", user1.getPassword());
        assertEquals(UserRole.USER, user1.getUserRole());

        assertEquals("test2", user6.getUsername());
        assertEquals("password2", user6.getPassword());
        assertEquals(UserRole.ADMIN, user6.getUserRole());
    }

    @Test
    void equalsTest() {
        assertEquals(user1, user1);
        assertEquals(user2, user2);
        assertEquals(user3, user3);
        assertEquals(user4, user4);
        assertEquals(user5, user5);
        assertEquals(user6, user6);

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertNotEquals(user1, user4);
        assertNotEquals(user1, user5);
        assertNotEquals(user1, user6);
    }

    @Test
    void hashcodeTest() {
        assertEquals(user1.hashCode(), user1.hashCode());
        assertEquals(user1.hashCode(), user2.hashCode());

        assertNotEquals(user1.hashCode(), user3.hashCode());
        assertNotEquals(user1.hashCode(), user4.hashCode());
        assertNotEquals(user1.hashCode(), user5.hashCode());
        assertNotEquals(user1.hashCode(), user6.hashCode());
    }
}
