package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyStatus {
    private String type;
    private String value;
    private String status;
}
