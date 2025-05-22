package co.com.pragma.usecase.key;

import co.com.pragma.model.key.*;
import co.com.pragma.model.key.config.PragmaException;
import co.com.pragma.model.key.gateways.IdentificationGateway;
import co.com.pragma.model.key.gateways.KeyGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static co.com.pragma.model.key.config.ErrorCode.BP409;
import static co.com.pragma.model.key.config.ErrorCode.BP409_1;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class KeyUseCaseTest {
    @Mock
    private IdentificationGateway identificationGateway;
    @Mock
    private KeyGateway keyGateway;
    @InjectMocks
    private KeyUseCase keyUseCase;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void save() {
        Identification identification = new Identification("CC", "1193134338");
        Card card = new Card("CUENTA_DE_AHORRO", "10234567890");
        CustomerInformation customerInformation = new CustomerInformation(identification, card);
        Key key = new Key("MSISDN", "2134567890");
        DataRegisterRequest dataRegisterRequest = new DataRegisterRequest(customerInformation, key);
        KeyInformation keyInformation = new KeyInformation("MSISDN", "2134567890", "ACTIVA", LocalDateTime.now().toString(), "10234567890", "1193134338");

        when(identificationGateway.getIdentificationId(dataRegisterRequest.getCustomerInformation().getIdentification().getNumber())).thenReturn(customerInformation);
        when(keyGateway.saveKey(keyInformation)).thenReturn(keyInformation);

        KeyInformation keyInformationTest = keyUseCase.save(dataRegisterRequest);

        Assertions.assertEquals(keyInformationTest.getType(), keyInformation.getType());
        Assertions.assertEquals(keyInformationTest.getValue(), keyInformation.getValue());
        Assertions.assertEquals(keyInformationTest.getStatus(), keyInformation.getStatus());
        Assertions.assertEquals(keyInformationTest.getCardId(), keyInformation.getCardId());
        Assertions.assertEquals(keyInformationTest.getCustomerId(), keyInformation.getCustomerId());
    }

    @Test
    void updateKey() {
        Key currentKey = new Key("MSISDN", "9876543210");
        Key newKey = new Key("MSISDN", "1020345678");
        KeyInformationUpdate keyInformationUpdate = new KeyInformationUpdate(currentKey, newKey);
        KeyInformation keyInformation = new KeyInformation("MSISDN", "9876543210", "ACTIVA", "2025-02-28T09:18:00", "10234567890", "1023456789");
        KeyInformation keyInformationUpdateData = new KeyInformation("MSISDN", "1020345678", "ACTIVA", "2025-02-28T09:18:00", "10234567890", "1023456789");
        KeyStatus keyStatus = new KeyStatus("MSISDN", "1020345678", "ACTIVA");

        when(keyGateway.keyById(keyInformationUpdate.getCurrentKey().getValue())).thenReturn(keyInformation);
        when(keyGateway.saveKey(any(KeyInformation.class))).thenReturn(keyInformationUpdateData);
        when(keyGateway.deleteKeyById(keyInformationUpdate.getCurrentKey().getValue())).thenReturn("Llave eliminada");

        KeyStatus keyStatusTest = keyUseCase.updateKey(keyInformationUpdate);

        Assertions.assertEquals(keyStatusTest, keyStatus);
    }

    @Test
    void updateStatus() {
        KeyStatus keyStatus = new KeyStatus("MSISDN", "3214508016", "BLOQUEAR");
        KeyInformation keyInformation = new KeyInformation("MSISDN", "3214508016", "ACTIVA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338");
        KeyInformation keyInformationUpdateStatus = new KeyInformation("MSISDN", "3214508016", "BLOQUEADA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338");
        KeyInformation keyInformationResponse = new KeyInformation("MSISDN", "3214508016", "BLOQUEADA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338");
        KeyStatus keyStatusResp = new KeyStatus("MSISDN", "3214508016", "BLOQUEADA");

        when(keyGateway.keyById(keyStatus.getValue())).thenReturn(keyInformation);
        when(keyGateway.saveKey(any(KeyInformation.class))).thenReturn(keyInformationResponse);

        KeyStatus keyStatusTest = keyUseCase.updateStatus(keyStatus);

        Assertions.assertEquals(keyStatusTest, keyStatusResp);
    }

    @Test
    void isEmptyCustomerInformation(){
        Identification identification = new Identification("CC", "1193134338");
        Card card = new Card("CUENTA_DE_AHORRO", "10234567890");
        CustomerInformation customerInformation = new CustomerInformation(identification, card);
        Key key = new Key("MSISDN", "2134567890");
        DataRegisterRequest dataRegisterRequest = new DataRegisterRequest(customerInformation, key);

        when(identificationGateway.getIdentificationId(dataRegisterRequest.getCustomerInformation().getIdentification().getNumber())).thenReturn(null);

        PragmaException exception = assertThrows(PragmaException.class, () -> {
            keyUseCase.save(dataRegisterRequest);
        });

        assertEquals(BP409, exception.getError());
    }

    @Test
    void isEmptyKey(){
        Key currentKey = new Key("MSISDN", "9876543210");
        Key newKey = new Key("MSISDN", "1020345678");
        KeyInformationUpdate keyInformationUpdate = new KeyInformationUpdate(currentKey, newKey);
        KeyStatus keyStatus = new KeyStatus("MSISDN", "3214508016", "BLOQUEAR");
        KeyInformation keyInformation = new KeyInformation("MSISDN", "3214508016", "ACTIVA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338");

        when(keyGateway.keyById(keyStatus.getValue())).thenReturn(keyInformation);

        PragmaException exception = assertThrows(PragmaException.class, () -> {
            keyUseCase.updateKey(keyInformationUpdate);
        });

        assertEquals(BP409_1, exception.getError());
    }

    @Test
    void isEmptyKeyInformation(){
        KeyStatus keyStatus = new KeyStatus("MSISDN", "3214508016", "BLOQUEAR");
        KeyInformation keyInformation = new KeyInformation("MSISDN", "3214508016", "ACTIVA", "2025-05-07T19:44:48.440109300", "10234567890", "1193134338");

        when(keyGateway.keyById(keyStatus.getValue())).thenReturn(null);

        PragmaException exception = assertThrows(PragmaException.class, () -> {
            keyUseCase.updateStatus(keyStatus);
        });

        assertEquals(BP409_1, exception.getError());
    }
}