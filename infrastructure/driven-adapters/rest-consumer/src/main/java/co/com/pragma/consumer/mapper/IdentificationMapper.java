package co.com.pragma.consumer.mapper;

import co.com.pragma.consumer.CardResponse;
import co.com.pragma.consumer.IdentificationResponse;
import co.com.pragma.consumer.ObjectResponse;
import co.com.pragma.model.key.Card;
import co.com.pragma.model.key.CustomerInformation;
import co.com.pragma.model.key.Identification;
import org.springframework.beans.BeanUtils;

public class IdentificationMapper {

    public static Identification identificationResponseToIdentification(IdentificationResponse identificationResponse){
        Identification identification = Identification.builder().build();
        BeanUtils.copyProperties(identificationResponse, identification);
        return identification;
    }

    public static Card cardResponseToCard(CardResponse cardResponse){
        Card card = Card.builder().build();
        BeanUtils.copyProperties(cardResponse, card);
        return card;
    }

    public static CustomerInformation customerInformation(ObjectResponse objectResponse){
        return CustomerInformation.builder()
                .identification(identificationResponseToIdentification(objectResponse.getCustomer().getIdentification()))
                .card(cardResponseToCard(objectResponse.getCustomer().getCard()))
                .build();
    }

}
