package com.truphone.jaderbittencourt.truphoneChallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidHexadecimalException extends RuntimeException {

    public InvalidHexadecimalException(String message) {
        super(message);
    }

}
