package co.com.pragma.consumer;

import co.com.pragma.consumer.mapper.IdentificationMapper;
import co.com.pragma.model.key.CustomerInformation;
import co.com.pragma.model.key.gateways.IdentificationGateway;
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
public class RestConsumer implements IdentificationGateway// implements Gateway from domain
{
    private final String url;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public RestConsumer(@Value("${adapter.restconsumer.url}") String url, OkHttpClient client, ObjectMapper mapper) {
        this.url = url;
        this.client = client;
        this.mapper = mapper;
    }

    @CircuitBreaker(name = "getIdentification", fallbackMethod = "fallbackMethod")
    public ObjectResponse getIdentification(String id) throws IOException {
        Request request = new Request.Builder()
                .url(url + id)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();
        return callAndMap(request, ObjectResponse.class);
    }

    public String fallbackMethod(Exception ignored) {
        return "¡No hubo respuesta del api consumida!";
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
                .addHeader("Content-Type", "application/json")
                .build();

        return callAndMap(request, ObjectResponse.class);
    }

    private <T> T callAndMap(Request request, Class<T> clazz) throws IOException {
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return mapper.readValue(response.body().string(), clazz);
        } else {
            return null;
        }
        //throw new IOException(response.toString());
    }

    @Override
    public CustomerInformation getIdentificationId(String id) {
        CustomerInformation customerInformation = null;
        try{
            ObjectResponse objectResponse = getIdentification(id);
            if (objectResponse != null) {
                customerInformation = IdentificationMapper.customerInformation(objectResponse);
            }
            return customerInformation;
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return customerInformation;
        }
    }
}
