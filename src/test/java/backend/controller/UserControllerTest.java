package backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UserControllerTest {

    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        testRestTemplate = new TestRestTemplate("user", "password");
    }

    @Test
    @Disabled
    void test() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("", String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
