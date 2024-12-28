package io.tdd.lectureservice.lecture.interfaces;

import io.tdd.lectureservice.lecture.domain.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l WHERE l.lectureDate BETWEEN :startDate AND :endDate AND l.capacity != 0")
    List<Lecture> findAllByLectureDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Lecture> findById(Long lectureId);

}
