package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataRegisterResponseDto {
    @NotNull
    @NotBlank(message = "El objeto keyInformationDto es obligatorio")
    private KeyInformationRegisterDto keyInformationDto;
}
