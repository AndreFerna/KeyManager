package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataUpdateStatusRequestDto {
    @NotNull
    @NotBlank(message = "El objeto requestInformation es obligatorio")
    private RequestInformationDto requestInformation;
    @NotNull
    @NotBlank(message = "El objeto customer es obligatorio")
    private CustomerDto customer;
    @NotNull
    @NotBlank(message = "El objeto key es obligatorio")
    private KeyStatusDto key;
}
