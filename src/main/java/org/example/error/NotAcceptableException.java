package org.example.error;

import org.springframework.http.HttpStatus;

public class NotAcceptableException extends ApiBaseException {

    public NotAcceptableException(String message) {
        super(message);
    }

    @Override
    HttpStatus getStatusCode() {
        return HttpStatus.NOT_ACCEPTABLE;
    }
}
