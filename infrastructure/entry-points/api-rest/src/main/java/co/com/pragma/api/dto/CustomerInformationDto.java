package co.com.pragma.api.dto;

import co.com.pragma.model.key.Card;
import co.com.pragma.model.key.Identification;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInformationDto {
    @NotNull
    @NotBlank(message = "El objeto identification es obligatorio")
    private Identification identification;
    @NotNull
    @NotBlank(message = "El objeto card es obligatorio")
    private Card card;
}
