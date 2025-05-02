package co.com.pragma.usecase.key;

import co.com.pragma.model.key.*;
import co.com.pragma.model.key.config.ErrorCode;
import co.com.pragma.model.key.config.PragmaException;
import co.com.pragma.model.key.gateways.IdentificationGateway;
import co.com.pragma.model.key.gateways.KeyGateway;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
public class KeyUseCase {

    //customer
    private final IdentificationGateway identificationGateway;
    private final KeyGateway keyGateway;


    public KeyInformation save(DataRegisterRequest dataRegisterRequest){
        CustomerInformation customerInformation = dataRegisterRequest.getCustomerInformation();
        Identification identification =  customerInformation.getIdentification();
        Key key = dataRegisterRequest.getKey();

        CustomerInformation customerInformationResponse = identificationGateway.getIdentificationId(identification.getNumber());

        if(Objects.isNull(customerInformationResponse) || Objects.isNull(customerInformationResponse.getIdentification()) || Objects.isNull(customerInformationResponse.getCard())){
            throw new PragmaException(ErrorCode.BP409);
        }

        KeyInformation keyInformationData = KeyInformation.builder()
                .value(key.getValue())
                .type(key.getType())
                .status("ACTIVA")
                .creationDate(LocalDateTime.now().toString())
                .cardNumber(customerInformation.getCard().getNumber())
                .customerNumber(identification.getNumber())
                .build();

        KeyInformation keyInformation = keyGateway.saveKey(keyInformationData);

        System.out.println("Llave Guardada --------------------------------------------------> " + keyInformation);
        return keyInformationData;
    }

}
