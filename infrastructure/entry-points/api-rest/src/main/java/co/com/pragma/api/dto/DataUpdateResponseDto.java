package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataUpdateResponseDto {
    @NotNull
    @NotBlank(message = "El objeto keyInformation es obligatorio")
    private KeyInformationUpdateKeyDto keyInformation;
}
