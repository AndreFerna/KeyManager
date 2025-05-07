package co.com.pragma.api;
import co.com.pragma.api.dto.*;
import co.com.pragma.api.dto.DataRegisterRequestDto;
import co.com.pragma.api.mapper.RegisterKeyMapper;
import co.com.pragma.api.mapper.UpdateKeyMapper;
import co.com.pragma.model.key.DataRegisterRequest;
import co.com.pragma.model.key.KeyInformation;
import co.com.pragma.model.key.KeyStatus;
import co.com.pragma.usecase.key.KeyUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/key-management/key-manager-api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class KeyApiRest {
//    private final MyUseCase useCase;
    private final KeyUseCase keyUseCase;


    @GetMapping(path = "/path")
    public String commandName() {
//      return useCase.doAction();
        return "Hello World";
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
        KeyStatus keyStatus = keyUseCase.updateKey(UpdateKeyMapper.toKeyInformationUpdate(dataUpdateKeyRequestDto.getKey()));
        KeyStatusDto keyStatusDto = UpdateKeyMapper.keyStatusToKeyStatusDto(keyStatus);
        return UpdateKeyMapper.toDataUpdateResponseDto(keyStatusDto);
    }

    @PutMapping(path = "/update-status")
    public DataUpdateResponseDto updateStatus(@RequestBody @Valid DataUpdateStatusRequestDto dataUpdateStatusRequestDto){
        return null;
    }
}
