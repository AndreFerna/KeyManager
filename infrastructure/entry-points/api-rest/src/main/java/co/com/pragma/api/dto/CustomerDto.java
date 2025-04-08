package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    @NotNull
    @NotBlank(message = "Parametro customer.type es obligatorio")
    @Size(min = 1, max = 20, message = "Parametro customer.type no cumple con la longitud")
    private String type;
    @NotNull
    @NotBlank(message = "Parametro customer.number es obligatorio")
    @Size(min = 1, max = 50, message = "Parametro customer.number no cumple con la longitud")
    @Pattern(regexp = "\\d", message = "Parametero customer.number no valido")
    private String number;
}
