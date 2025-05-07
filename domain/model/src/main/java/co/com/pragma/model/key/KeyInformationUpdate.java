package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyInformationUpdate {
    private Key currentKey;
    private Key newKey;
}
