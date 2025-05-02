package co.com.pragma.consumer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CardResponse {
    private String type;
    private String number;
}
