package med.api.infra.exception;

import org.springframework.validation.FieldError;

public record DataErrorValidation(String field, String message) {
   
    public DataErrorValidation(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

}