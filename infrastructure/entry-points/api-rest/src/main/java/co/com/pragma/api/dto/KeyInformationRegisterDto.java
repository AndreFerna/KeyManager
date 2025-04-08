package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyInformationRegisterDto {
    @NotNull
    @NotBlank(message = "El objeto key es obligatorio")
    private CompleteKeyInformationDto key;
    @NotNull
    @NotBlank(message = "El objeto card es obligatorio")
    private CardDto card;
}
