package co.com.pragma.api;
import co.com.pragma.api.dto.*;
import co.com.pragma.api.dto.DataRegisterRequestDto;
import co.com.pragma.api.mapper.RegisterKeyMapper;
import co.com.pragma.api.mapper.UpdateKeyMapper;
import co.com.pragma.api.mapper.UpdateStatusMapper;
import co.com.pragma.model.key.DataRegisterRequest;
import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.model.key.KeyInformationUpdate;
import co.com.pragma.model.key.KeyStatus;
import co.com.pragma.model.key.config.ErrorCode;
import co.com.pragma.model.key.config.PragmaException;
import co.com.pragma.usecase.key.KeyUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/key-management/key-manager-api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class KeyApiRest {

    private final KeyUseCase keyUseCase;

    private HealthEndpoint healthEndpoint;

    @RequestMapping(path = "/health", method = RequestMethod.HEAD)
    public ResponseEntity<Void> health() {
        HealthComponent healthComponent = healthEndpoint.health();

        if (Status.UP.equals(healthComponent.getStatus())) {
            return ResponseEntity.ok().build();
        } else {
            throw new PragmaException(ErrorCode.SP503);
        }
    }

    @PostMapping(path = "/register-key")
    public DataRegisterResponseDto registerKey(@RequestBody @Valid DataRegisterRequestDto dataRegisterRequestDto){
        DataRegisterRequest dataRegisterRequest = RegisterKeyMapper.registerRequestDtoToRegister(dataRegisterRequestDto);
        KeyInformation keyInformation = keyUseCase.save(dataRegisterRequest);
        DataRegisterResponseDto dataRegisterResponseDto = RegisterKeyMapper.toRegisterResponseDto(keyInformation, dataRegisterRequestDto.getCustomerInformation().getCard());
        return dataRegisterResponseDto;
    }

    @PutMapping(path = "/update-key")
    public DataUpdateResponseDto updateKey(@RequestBody @Valid DataUpdateKeyRequestDto dataUpdateKeyRequestDto){
        KeyInformationUpdate keyInformationUpdate = UpdateKeyMapper.toKeyInformationUpdate(dataUpdateKeyRequestDto.getKey());
        KeyStatus keyStatus = keyUseCase.updateKey(keyInformationUpdate);
        KeyStatusDto keyStatusDto = UpdateKeyMapper.keyStatusToKeyStatusDto(keyStatus);
        return UpdateKeyMapper.toDataUpdateResponseDto(keyStatusDto);
    }

    @PutMapping(path = "/update-status")
    public DataUpdateResponseDto updateStatus(@RequestBody @Valid DataUpdateStatusRequestDto dataUpdateStatusRequestDto){
        KeyStatus keyStatusUseCase = UpdateStatusMapper.keyStatusDtoToKeyStatus(dataUpdateStatusRequestDto.getKey());
        KeyStatus keyStatus = keyUseCase.updateStatus(keyStatusUseCase);
        KeyStatusDto keyStatusDto = UpdateStatusMapper.keyStatusToKeyStatusDto(keyStatus);
        return UpdateStatusMapper.toDataUpdateResponseDto(keyStatusDto);
    }
}
