package co.com.pragma.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataRegisterRequestDto {
    @NotNull
    @Valid
    private RequestInformationDto requestInformation;
    @NotNull
    @Valid
    private CustomerInformationDto customerInformation;
    @NotNull
    @Valid
    private KeyDto key;
}
