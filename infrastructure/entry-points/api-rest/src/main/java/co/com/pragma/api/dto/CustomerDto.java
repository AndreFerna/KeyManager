package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    @NotNull
    @Size(min = 1, max = 20)
    private String type;
    @NotNull
    @Size(min = 1, max = 50)
    //@Pattern(regexp = "\\d")
    private String number;
}
