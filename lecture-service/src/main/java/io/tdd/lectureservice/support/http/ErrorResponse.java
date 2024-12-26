package io.tdd.lectureservice.support.http;

public record ErrorResponse(
        String code,
        String message
) {

}
