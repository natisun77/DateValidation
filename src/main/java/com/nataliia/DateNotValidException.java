package com.nataliia;

public class DateNotValidException extends IllegalArgumentException {

    public DateNotValidException(String message) {
        super(message);
    }
}
