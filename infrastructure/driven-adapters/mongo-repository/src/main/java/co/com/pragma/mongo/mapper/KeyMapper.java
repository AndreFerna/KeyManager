package co.com.pragma.mongo.mapper;

import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.mongo.entities.KeyEntity;

public class KeyMapper {

    public static KeyEntity toEntity(KeyInformation keyInformation){
        return KeyEntity.builder()
                .value(keyInformation.getValue())
                .type(keyInformation.getType())
                .status(keyInformation.getStatus())
                .creationDate(keyInformation.getCreationDate())
                .customerId(keyInformation.getCustomerId())
                .cardId(keyInformation.getCardId())
                .build();
    }

    public static KeyInformation toDomain(KeyEntity keyEntity){
        return KeyInformation.builder()
                .value(keyEntity.getValue())
                .type(keyEntity.getType())
                .status(keyEntity.getStatus())
                .creationDate(keyEntity.getCreationDate())
                .customerId(keyEntity.getCustomerId())
                .cardId(keyEntity.getCardId())
                .build();
    }

}
