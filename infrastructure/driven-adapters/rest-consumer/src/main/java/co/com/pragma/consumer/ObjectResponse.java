package co.com.pragma.consumer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjectResponse {
    private String id;
    private CustomerResponse customer;
}