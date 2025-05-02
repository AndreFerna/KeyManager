package co.com.pragma.api.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyInformationUpdateKeyDto {
    @NotNull
    @Valid
    private KeyStatusDto key;
}
