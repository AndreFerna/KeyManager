package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompleteKeyInformationDto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    private String type;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String value;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    private String status;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$")
    private String creationDate;
}
