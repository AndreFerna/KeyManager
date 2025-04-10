package co.com.pragma.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RestConsumer // implements Gateway from domain
{
    private final String url;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public RestConsumer(@Value("${adapter.restconsumer.url}") String url, OkHttpClient client, ObjectMapper mapper) {
        this.url = url;
        this.client = client;
        this.mapper = mapper;
    }

    // these methods are an example that illustrates the implementation of OKHTTP Client.
    // You should use the methods that you implement from the Gateway from the domain.

    @CircuitBreaker(name = "testGet", fallbackMethod = "testGetOk") // this name should match with settings name in application.yaml
    public ObjectResponse testGet() throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        return callAndMap(request, ObjectResponse.class);
    }

    public String testGetOk(Exception ignored) {
        return "fallback";
    }

    @CircuitBreaker(name = "testPost") // this name should match with settings name in application.yaml
    public ObjectResponse testPost() throws IOException {
        String json = mapper.writeValueAsString(ObjectRequest.builder()
            .val1("exampleval1")
            .val2("exampleval1")
            .build()
        );

        RequestBody requestBody = RequestBody
            .create(json, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type","application/json")
            .build();

        return callAndMap(request, ObjectResponse.class);
    }

    private <T> T callAndMap(Request request, Class<T> clazz) throws IOException {
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return mapper.readValue(response.body().string(), clazz);
        }
        throw new IOException(response.toString());
    }
}
