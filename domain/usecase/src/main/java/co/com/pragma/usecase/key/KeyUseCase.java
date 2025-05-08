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
                .cardId(customerInformation.getCard().getNumber())
                .customerId(identification.getNumber())
                .build();

        KeyInformation keyInformation = keyGateway.saveKey(keyInformationData);

        return keyInformationData;
    }

    public KeyStatus updateKey(KeyInformationUpdate keyInformationUpdate) {

        KeyInformation keyInformation = keyGateway.keyById(keyInformationUpdate.getCurrentKey().getValue());

        if (Objects.isNull(keyInformation)) {
            throw new PragmaException(ErrorCode.BP409_1);
        }

        KeyInformation keyInformationData = KeyInformation.builder()
                .value(keyInformationUpdate.getNewKey().getValue())
                .type(keyInformationUpdate.getNewKey().getType())
                .status(keyInformation.getStatus())
                .creationDate(keyInformation.getCreationDate())
                .cardId(keyInformation.getCardId())
                .customerId(keyInformation.getCustomerId())
                .build();
        KeyInformation keyInformationResp = keyGateway.saveKey(keyInformationData);

        KeyStatus keyStatus = KeyStatus.builder()
                .type(keyInformationResp.getType())
                .value(keyInformationResp.getValue())
                .status(keyInformationResp.getStatus())
                .build();

        keyGateway.deleteKeyById(keyInformationUpdate.getCurrentKey().getValue());
        return keyStatus;
    }

    public KeyStatus updateStatus(KeyStatus keyStatus){
        KeyInformation keyInformation = keyGateway.keyById(keyStatus.getValue());

        if (Objects.isNull(keyInformation)) {
            throw new PragmaException(ErrorCode.BP409_1);
        }

        String status = "";

        if(keyStatus.getStatus().equals("BLOQUEAR")){
            status = "BLOQUEADA";
        }
        else if (keyStatus.getStatus().equals("DESBLOQUEAR")) {
            status = "ACTIVA";
        } else if (keyStatus.getStatus().equals("CANCELAR")) {
            status = "CANCELADA";
        }

        KeyInformation keyInformationData = KeyInformation.builder()
                .value(keyInformation.getValue())
                .type(keyInformation.getType())
                .status(status)
                .creationDate(keyInformation.getCreationDate())
                .cardId(keyInformation.getCardId())
                .customerId(keyInformation.getCustomerId())
                .build();

        KeyInformation keyInformationResp = keyGateway.saveKey(keyInformationData);

        KeyStatus keyStatusResp = KeyStatus.builder()
                .type(keyInformationResp.getType())
                .value(keyInformationResp.getValue())
                .status(keyInformationResp.getStatus())
                .build();

        return keyStatusResp;
    }

}
