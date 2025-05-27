package co.com.pragma.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyInformationUpdateDto {
    @NotNull
    @Valid
    private KeyDto currentKey;
    @NotNull
    @Valid
    private KeyDto newKey;
}
