package io.tdd.lectureservice.reservation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tdd.lectureservice.lecture.domain.Lecture;
import io.tdd.lectureservice.reservation.interfaces.dto.request.ReservationRequest;
import io.tdd.lectureservice.user.domain.User;
import io.tdd.lectureservice.support.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "tb_reservation", uniqueConstraints ={@UniqueConstraint(name = "USER_LECTURE_UNIQUE", columnNames = {"user_id", "lecture_id"})})
public class Reservation extends BaseEntity {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    private String subject;

    private String speaker;

    @Column(name = "lecture_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lectureDate;

    public Reservation(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
        this.subject = lecture.getSubject();
        this.speaker = lecture.getSpeakerName();
        this.lectureDate = lecture.getLectureDate();
    }

}
