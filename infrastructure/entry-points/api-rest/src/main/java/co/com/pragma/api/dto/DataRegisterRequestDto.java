package co.com.pragma.api.dto;

import co.com.pragma.model.key.CustomerInformation;
import co.com.pragma.model.key.Key;
import co.com.pragma.model.key.RequestInformation;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataRegisterRequestDto {
    @NotNull
    @NotBlank(message = "El objeto requestInformation es obligatorio")
    private RequestInformation requestInformation;
    @NotNull
    @NotBlank(message = "El objeto customerInformation es obligatorio")
    private CustomerInformation customerInformation;
    @NotNull
    @NotBlank(message = "El objeto key es obligatorio")
    private Key key;
}
