package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyStatusDto {
    @NotNull
    @NotBlank(message = "Parametro key.type es obligatorio")
    @Size(min = 1, max = 20, message = "Parametro key.type no cumple con la longitud")
    private String type;
    @NotNull
    @NotBlank(message = "Parametro key.number es obligatorio")
    @Size(min = 1, max = 50, message = "Parametro key.number no cumple con la longitud")
    private String value;
    @NotNull
    @NotBlank(message = "Parametro key.status es obligatorio")
    @Size(min = 1, max = 20, message = "Parametro key.status no cumple con la longitud")
    private String status;

}
