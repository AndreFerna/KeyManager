package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.DataUpdateResponseDto;
import co.com.pragma.api.dto.KeyInformationUpdateKeyDto;
import co.com.pragma.api.dto.KeyStatusDto;
import co.com.pragma.model.key.KeyStatus;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class UpdateStatusMapper {

    public KeyStatus keyStatusDtoToKeyStatus(KeyStatusDto keyStatusDto){
        KeyStatus keyStatus = KeyStatus.builder().build();
        BeanUtils.copyProperties(keyStatusDto, keyStatus);
        return keyStatus;
    }

    public static KeyStatusDto keyStatusToKeyStatusDto(KeyStatus keyStatus){
        return KeyStatusDto.builder()
                .type(keyStatus.getType())
                .value(keyStatus.getValue())
                .status(keyStatus.getStatus())
                .build();
    }

    public static DataUpdateResponseDto toDataUpdateResponseDto (KeyStatusDto keyStatusDto){
        KeyInformationUpdateKeyDto keyInformationUpdateKeyDto = KeyInformationUpdateKeyDto.builder()
                .key(keyStatusDto)
                .build();
        return DataUpdateResponseDto.builder()
                .keyInformation(keyInformationUpdateKeyDto)
                .build();
    }



}
