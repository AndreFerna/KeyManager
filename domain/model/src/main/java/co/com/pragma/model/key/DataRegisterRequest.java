package co.com.pragma.model.key;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class DataRegisterRequest {
    private CustomerInformation customerInformation;
    private Key key;
}
