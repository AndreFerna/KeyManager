package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.*;
import co.com.pragma.model.key.Key;
import co.com.pragma.model.key.KeyInformationUpdate;
import co.com.pragma.model.key.KeyStatus;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class UpdateKeyMapper {

    public static Key keyDtoToKey(KeyDto keyDto){
        Key key = Key.builder().build();
        BeanUtils.copyProperties(keyDto, key);
        return key;
    }

    public static KeyStatusDto keyStatusToKeyStatusDto(KeyStatus keyStatus){
        return KeyStatusDto.builder()
                .type(keyStatus.getType())
                .value(keyStatus.getValue())
                .status(keyStatus.getStatus())
                .build();
    }

    public static DataUpdateResponseDto toDataUpdateResponseDto(KeyStatusDto keyStatusDto){
        KeyInformationUpdateKeyDto keyInformationUpdateKeyDto = KeyInformationUpdateKeyDto.builder()
                .key(keyStatusDto)
                .build();

        return DataUpdateResponseDto.builder()
                .keyInformation(keyInformationUpdateKeyDto)
                .build();
    }

    public static KeyInformationUpdate toKeyInformationUpdate(KeyInformationUpdateDto keyInformationUpdateDto){
        return KeyInformationUpdate.builder()
                .currentKey(keyDtoToKey(keyInformationUpdateDto.getCurrentKey()))
                .newKey(keyDtoToKey(keyInformationUpdateDto.getNewKey()))
                .build();
    }

}
