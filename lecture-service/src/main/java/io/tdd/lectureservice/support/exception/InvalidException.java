package io.tdd.lectureservice.support.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message) {
        super(message);
    }
}
