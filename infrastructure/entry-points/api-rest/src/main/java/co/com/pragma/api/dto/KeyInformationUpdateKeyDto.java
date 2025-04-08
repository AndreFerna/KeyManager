package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyInformationUpdateKeyDto {
    @NotNull
    @NotBlank(message = "El objeto key es obligatorio")
    private KeyStatusDto key;
}
