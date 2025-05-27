package co.com.pragma.api;

import co.com.pragma.api.dto.*;
import co.com.pragma.model.key.*;
import co.com.pragma.usecase.key.KeyUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static co.com.pragma.model.key.config.ErrorCode.SP503;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KeyApiRest.class)
@ContextConfiguration(classes = {KeyApiRest.class})
class ApiRestTest {

    //KeyApiRest apiRest = new KeyApiRest();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeyUseCase keyUseCase;

    @MockBean
    private HealthEndpoint healthEndpoint;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void apiRestTest() {
        //var response = apiRest.commandName();
        //assertEquals("Hello World", response);
    }

    @Test
    void testHealthUp() throws Exception {
        when(healthEndpoint.health()).thenReturn(Health.up().build());

        mockMvc.perform(head("/v1/key-management/key-manager-api/health"))
                .andExpect(status().isOk());
    }

    @Test
    void testHealthDown() throws Exception {
        when(healthEndpoint.health()).thenReturn(Health.down().build());

        ServletException exception = assertThrows(ServletException.class, () -> {
            mockMvc.perform(head("/v1/key-management/key-manager-api/health"));
        });

        assertTrue(exception.getMessage().contains(SP503.getDetail()));
    }

    @Test
    void testRegisterKey() throws Exception {
        RequestInformationDto requestInformationDto = new RequestInformationDto("1234", "Principal");
        IdentificationDto identificationDto = new IdentificationDto("CC", "1193134338");
        CardDto cardDto = new CardDto("CUENTA_DE_AHORRO", "10234567890");
        CustomerInformationDto customerInformationDto = new CustomerInformationDto(identificationDto, cardDto);
        KeyDto keyDto = new KeyDto("MSISDN", "3146304163");
        DataRegisterRequestDto dataRegisterRequestDto = new DataRegisterRequestDto(requestInformationDto, customerInformationDto, keyDto);
        Identification identification = new Identification("CC", "1193134338");
        Card card = new Card("CUENTA_DE_AHORRO", "10234567890");
        CustomerInformation customerInformation = new CustomerInformation(identification, card);
        Key key = new Key("MSISDN", "3146304163");
        DataRegisterRequest dataRegisterRequest = new DataRegisterRequest(customerInformation, key); // Agrega setters si es necesario
        KeyInformation keyInformation = new KeyInformation("MSISDN", "3146304163", "ACTIVA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338"); // Simula lo que retorna el caso de uso

        when(keyUseCase.save(any(DataRegisterRequest.class))).thenReturn(keyInformation);

        mockMvc.perform(post("/v1/key-management/key-manager-api/register-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataRegisterRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keyInformation").exists()); // Asumiendo que el campo se llama card en el response
    }

    @Test
    void testUpdateKey() throws Exception {
        RequestInformationDto requestInformationDto = new RequestInformationDto("1234", "Principal");
        CustomerDto customerDto = new CustomerDto("CC", "1193134338");
        KeyDto current = new KeyDto("MSISDN", "3146304163");
        KeyDto newKeyRequest = new KeyDto("MSISDN", "3214508016");
        KeyInformationUpdateDto keyInformationUpdateDto = new KeyInformationUpdateDto(current, newKeyRequest);
        DataUpdateKeyRequestDto dataUpdateKeyRequestDto = new DataUpdateKeyRequestDto(requestInformationDto, customerDto, keyInformationUpdateDto);

        Key currentKey = new Key("MSISDN", "3146304163");
        Key newKey = new Key("MSISDN", "3214508016");
        KeyInformationUpdate keyInformationUpdate = new KeyInformationUpdate(currentKey, newKey);
        KeyStatus keyStatus = new KeyStatus("MSISDN", "3214508016", "ACTIVO"); // Devuelto por el caso de uso

        KeyStatusDto keyStatusDto = new KeyStatusDto("MSISDN", "3214508016", "ACTIVO");
        KeyInformationUpdateKeyDto keyInformationUpdateKeyDto = new KeyInformationUpdateKeyDto(keyStatusDto);
        DataUpdateResponseDto dataUpdateResponseDto = new DataUpdateResponseDto(keyInformationUpdateKeyDto);

        when(keyUseCase.updateKey(any(KeyInformationUpdate.class))).thenReturn(keyStatus);

        mockMvc.perform(put("/v1/key-management/key-manager-api/update-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataUpdateKeyRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateStatus() throws Exception {
        RequestInformationDto requestInformationDto = new RequestInformationDto("1234", "Principal");
        CustomerDto customerDto = new CustomerDto("CC", "1193134338");
        KeyStatusDto keyStatusDto = new KeyStatusDto("MSISDN", "3214508016", "ACTIVO");

        DataUpdateStatusRequestDto requestDto = new DataUpdateStatusRequestDto(requestInformationDto, customerDto, keyStatusDto);

        KeyStatus status = new KeyStatus("MSISDN", "3214508016", "ACTIVO");
        when(keyUseCase.updateStatus(any(KeyStatus.class))).thenReturn(status);

        mockMvc.perform(put("/v1/key-management/key-manager-api/update-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

}
