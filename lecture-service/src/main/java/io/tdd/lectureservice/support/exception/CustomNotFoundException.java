package io.tdd.lectureservice.support.exception;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String object) {
        super(object + "가 존재하지 않습니다.");
    }
}
