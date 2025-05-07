package co.com.pragma.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
