package io.tdd.lectureservice.reservation.interfaces.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tdd.lectureservice.reservation.domain.Reservation;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponse {
    // 특강 ID
    private Long lectureId;

    // 특강 이름
    private String subject;

    // 강연자 정보
    private String speaker;

    // 강연 시간
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm", timezone = "Asia/Seoul")
    private LocalDateTime lectureDate;

    public ReservationResponse(Reservation reservation) {
        this.lectureId = reservation.getId();
        this.subject = reservation.getSubject();
        this.speaker = reservation.getSpeaker();
        this.lectureDate = reservation.getLectureDate();
    }
}
