package co.com.pragma.api;

import co.com.pragma.model.key.config.ErrorCode;
import co.com.pragma.model.key.config.GeneralExceptionResponse;
import co.com.pragma.model.key.config.Errors;
import co.com.pragma.model.key.config.PragmaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralExceptionResponse> handleConstraintViolationException(MethodArgumentNotValidException e){
        return genericHandleException(ErrorCode.SA400);
    }

    @ExceptionHandler(PragmaException.class)
    public ResponseEntity<GeneralExceptionResponse> handlePragmaException(PragmaException e){
        return genericHandleException(e.getError());
    }

    private ResponseEntity<GeneralExceptionResponse> genericHandleException(ErrorCode errorCode){
        Errors errors = Errors.builder()
                .code(errorCode.getCode())
                .detail(errorCode.getDetail())
                .build();

        ArrayList<Errors> errorsArrayList = new ArrayList<>();
        errorsArrayList.add(errors);

        GeneralExceptionResponse generalExceptionResponse = GeneralExceptionResponse.builder()
                .status(errorCode.getStatus())
                .title(errorCode.getTitle())
                .errors(errorsArrayList)
                .build();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(generalExceptionResponse);
    }

}
