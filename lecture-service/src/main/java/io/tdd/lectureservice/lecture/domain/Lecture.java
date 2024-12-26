package io.tdd.lectureservice.lecture.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tdd.lectureservice.support.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "tb_lecture")
@RequiredArgsConstructor
@AllArgsConstructor
public class Lecture extends BaseEntity {

    @Id
    @Column(name = "lecture_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "capacity")
    @ColumnDefault("30")
    private int capacity;

    @Column(name = "speaker")
    private String speakerName;

    @Column(name = "lecture_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate lectureDate;

}
