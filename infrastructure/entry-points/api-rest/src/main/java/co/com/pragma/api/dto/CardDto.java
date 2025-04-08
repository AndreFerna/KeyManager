package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {
    @NotNull
    @NotBlank(message = "Parametro card.type es obligatorio")
    @Size(min = 1, max = 30, message = "Parametro card.type no cumple con la longitud")
    private String type;
    @NotNull
    @NotBlank(message = "Parametro card.number es obligatorio")
    @Size(min = 1, max = 16, message = "Parametro card.number no cumple con la longitud")
    @Pattern(regexp = "\\d", message = "Parametro card.number no valido")
    private String number;
}
