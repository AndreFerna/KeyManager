package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class DataUpdateStatusRequest {
    private Identification customer;
    private KeyStatus keyStatus;
}
