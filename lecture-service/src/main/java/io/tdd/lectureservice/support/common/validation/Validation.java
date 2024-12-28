package io.tdd.lectureservice.support.common.validation;

import io.tdd.lectureservice.support.exception.InvalidException;

public class Validation {
    public static void checkId(String object, long id) {
        if (id < 1) throw new InvalidException(object +"의" + id +"가 유효하지 않습니다.");
    }

}
