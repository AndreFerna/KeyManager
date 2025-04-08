package co.com.pragma.api;
import co.com.pragma.api.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/key-management/key-manager-api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class KeyApiRest {
//    private final MyUseCase useCase;


    @GetMapping(path = "/path")
    public String commandName() {
//      return useCase.doAction();
        return "Hello World";
    }

    @PostMapping(path = "/register-key")
    public DataRegisterResponseDto registerKey(@RequestHeader("message-id") String messageId, @RequestBody @Valid DataRegisterRequestDto dataRegisterRequestDto){
        return null;
    }

    @PutMapping(path = "/update-key")
    public DataUpdateResponseDto updateKey(@RequestBody @Valid DataUpdateKeyRequestDto dataUpdateKeyRequestDto){
        return null;
    }

    @PutMapping(path = "/update-status")
    public DataUpdateResponseDto updateStatus(@RequestBody @Valid DataUpdateStatusRequestDto dataUpdateStatusRequestDto){
        return null;
    }
}
