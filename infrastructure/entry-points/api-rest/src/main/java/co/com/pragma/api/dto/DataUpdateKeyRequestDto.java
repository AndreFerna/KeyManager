package co.com.pragma.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataUpdateKeyRequestDto {
    @NotNull
    @Valid
    private RequestInformationDto requestInformation;
    @NotNull
    @Valid
    private CustomerDto customer;
    @NotNull
    @Valid
    private KeyDto key;
}
