package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyInformationRegisterDto {
    @NotNull
    private CompleteKeyInformationDto key;
    @NotNull
    private CardDto card;
}
