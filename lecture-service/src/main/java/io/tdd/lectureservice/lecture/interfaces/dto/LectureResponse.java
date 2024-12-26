package io.tdd.lectureservice.lecture.interfaces.dto;

import io.tdd.lectureservice.lecture.domain.Lecture;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureResponse {

    private String subject;

    private String speaker;

    private LocalDate lectureDate;

    private int capacity;

    public LectureResponse(Lecture lecture) {
        this.subject = lecture.getSubject();
        this.speaker = lecture.getSpeakerName();
        this.lectureDate = lecture.getLectureDate();
        this.capacity = lecture.getCapacity();
    }

}
