package co.com.pragma.consumer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponse {
    private IdentificationResponse identification;
    private CardResponse card;
}
