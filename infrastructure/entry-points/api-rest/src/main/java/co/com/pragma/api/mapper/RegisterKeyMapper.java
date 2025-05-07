package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.*;
import co.com.pragma.model.key.*;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class RegisterKeyMapper {

    public static Identification identificationDtoToIdentification(IdentificationDto identificationDto){
        Identification identification = Identification.builder().build();
        BeanUtils.copyProperties(identificationDto, identification);
        return identification;
    }

    public static Card cardDtoToCard(CardDto cardDto){
        Card card = Card.builder().build();
        BeanUtils.copyProperties(cardDto, card);
        return card;
    }

    public static CustomerInformation customerInformationDtoToCustomerInformation(CustomerInformationDto customerInformationDto){
        return CustomerInformation.builder()
                .identification(identificationDtoToIdentification(customerInformationDto.getIdentification()))
                .card(cardDtoToCard(customerInformationDto.getCard()))
                .build();
    }

    public static Key keyDtoToKey(KeyDto keyDto){
        Key key = Key.builder().build();
        BeanUtils.copyProperties(keyDto, key);
        return key;
    }

    public static DataRegisterRequest registerRequestDtoToRegister(DataRegisterRequestDto dataRegisterRequestDto) {
        return DataRegisterRequest.builder()
                .customerInformation(customerInformationDtoToCustomerInformation(dataRegisterRequestDto.getCustomerInformation()))
                .key(keyDtoToKey(dataRegisterRequestDto.getKey()))
                .build();
    }

    public static CompleteKeyInformationDto keyInformationToCompleteKeyInformationDto(KeyInformation keyInformation){
        return CompleteKeyInformationDto.builder()
                .type(keyInformation.getType())
                .value(keyInformation.getValue())
                .status(keyInformation.getStatus())
                .creationDate(keyInformation.getCreationDate())
                .build();
    }

    public static KeyInformationRegisterDto toKeyInformationRegisterDto(CompleteKeyInformationDto completeKeyInformationDto, CardDto cardDto){
        return  KeyInformationRegisterDto.builder()
                .key(completeKeyInformationDto)
                .card(cardDto)
                .build();
    }

    public static DataRegisterResponseDto toRegisterResponseDto(KeyInformation keyInformation, CardDto cardDto){
        CompleteKeyInformationDto completeKeyInformationDto = keyInformationToCompleteKeyInformationDto(keyInformation);
        return DataRegisterResponseDto.builder()
                .keyInformation(toKeyInformationRegisterDto(completeKeyInformationDto, cardDto))
                .build();
    }

}
