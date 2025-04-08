package co.com.pragma.api.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompleteKeyInformationDto {
    @NotNull
    @NotBlank(message = "Parametro key.type es obligatorio")
    @Size(min = 1, max = 20, message = "Parametro key.type no cumple con la longitud")
    private String type;
    @NotNull
    @NotBlank(message = "Parametro key.number es obligatorio")
    @Size(min = 1, max = 50, message = "Parametro key.number no cumple con la longitud")
    private String value;
    @NotNull
    @NotBlank(message = "Parametro key.status es obligatorio")
    @Size(min = 1, max = 20, message = "Parametro key.status no cumple con la longitud")
    private String status;
    @NotNull
    @NotBlank(message = "Parametro key.creationDate es obligatorio")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = "Parametro key.creationDate debe tener el formato yyyy-MM-dd'T'HH:mm:ss")
    private String creationDate;
}
