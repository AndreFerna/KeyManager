package co.com.pragma.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiRestTest {

    KeyApiRest apiRest = new KeyApiRest();

    @Test
    void apiRestTest() {
        var response = apiRest.commandName();
        assertEquals("Hello World", response);
    }
}
