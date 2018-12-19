package com.fun4.checkers.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

  private User user1;
  private User user2;
  private User user3;

  @Before
  public void setUp() {
    user1 = new User("user1", "password");
    user2 = new User("user2", "password");
    user3 = new User("user1", "password");
  }

  @Test
  public void constructorTest() {
    assertEquals("user1", user1.getUsername());
    assertEquals("password", user1.getPassword());
  }

  @Test
  public void equalsTest() {
    assertEquals(user1, user1);
    assertNotEquals(user1, user2);
    assertEquals(user1, user3);
  }

  @Test
  public void hashcodeTest() {
    assertEquals(user1.hashCode(), user1.hashCode());
    assertNotEquals(user1.hashCode(), user2.hashCode());
    assertEquals(user1.hashCode(), user3.hashCode());
  }
}
