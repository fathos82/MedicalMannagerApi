package med.vol.api.error;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleException404() {
        return ResponseEntity.notFound().build();

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleException400(MethodArgumentNotValidException e) {
        List<FieldError> fieldError = e.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldError.stream().map(ErrorValidation::new).toList());
    }



    private  record ErrorValidation(String field, String message) {
        ErrorValidation(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }


}
