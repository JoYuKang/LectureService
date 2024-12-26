package io.tdd.lectureservice.support.exception;

public class MaxCapacityException extends RuntimeException{
    public MaxCapacityException() {
        super("수강 인원이 꽉 찼습니다. 더 이상 예약할 수 없습니다.");
    }
}
