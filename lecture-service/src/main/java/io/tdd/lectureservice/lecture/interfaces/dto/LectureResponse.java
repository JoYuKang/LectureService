package io.tdd.lectureservice.lecture.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tdd.lectureservice.lecture.domain.Lecture;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureResponse {

    private String subject;

    private String speaker;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm", timezone = "Asia/Seoul")
    private LocalDateTime lectureDate;

    private int capacity;

    public LectureResponse(Lecture lecture) {
        this.subject = lecture.getSubject();
        this.speaker = lecture.getSpeakerName();
        this.lectureDate = lecture.getLectureDate();
        this.capacity = lecture.getCapacity();
    }

}
