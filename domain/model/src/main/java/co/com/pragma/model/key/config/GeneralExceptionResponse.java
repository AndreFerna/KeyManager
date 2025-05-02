package co.com.pragma.model.key.config;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionError {
    private int status;
    private String title;
    private ArrayList<Errors> errors;
}
