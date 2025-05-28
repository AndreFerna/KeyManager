package co.com.pragma.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.test.util.ReflectionTestUtils;
import java.io.IOException;

import static java.rmi.server.LogStream.log;


@Slf4j
public class RestConsumerTest {

    private static RestConsumer restConsumer;

    private static MockWebServer mockBackEnd;


    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        OkHttpClient client = new OkHttpClient.Builder().build();

        String url = mockBackEnd.url("url").toString();
        restConsumer = new RestConsumer(url, client, new ObjectMapper());
    }

    @AfterAll
    static void tearDown() throws IOException {

        mockBackEnd.shutdown();
    }

    @Test
    @DisplayName("Validate the function testGet.")
    void validateTestGet() throws IOException {
        String id = "1193134338";
        mockBackEnd.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody("{\"id\" : \"1193134338\"}"));

        var response = restConsumer.getIdentification(id);
        Assertions.assertEquals(id, response.getId());
    }

}