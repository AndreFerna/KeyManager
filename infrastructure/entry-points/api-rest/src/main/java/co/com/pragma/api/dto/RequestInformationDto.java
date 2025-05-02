package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInformationDto {
    @NotNull
    @Size(min = 1, max = 40)
    private String identifier;
    @NotNull
    @Size(min = 1, max = 50)
    private String consumer;
}
