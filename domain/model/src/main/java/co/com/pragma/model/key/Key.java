package co.com.pragma.model.key;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Key {
    private String type;
    private String value;
}