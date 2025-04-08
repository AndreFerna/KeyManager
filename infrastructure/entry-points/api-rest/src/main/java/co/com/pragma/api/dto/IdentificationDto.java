package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdentificationDto {
    @NotNull
    @NotBlank(message = "Parametro identification.type es obligatorio")
    @Size(min = 1, max = 20, message = "Parametro identification.type no cumple con la longitud")
    private String type;
    @NotNull
    @NotBlank(message = "Parametro identification.number es obligatorio")
    @Size(min = 1, max = 50, message = "Parametro identification.number no cumple con la longitud")
    @Pattern(regexp = "\\d", message = "Parametero identification.number no valido")
    private String number;
}
