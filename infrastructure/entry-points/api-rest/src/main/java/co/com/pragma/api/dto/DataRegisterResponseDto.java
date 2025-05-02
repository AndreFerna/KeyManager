package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataRegisterResponseDto {
    @NotNull
    private KeyInformationRegisterDto keyInformation;
}
