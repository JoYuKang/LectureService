package io.tdd.lectureservice.reservation.interfaces;

import io.tdd.lectureservice.lecture.domain.Lecture;
import io.tdd.lectureservice.reservation.domain.Reservation;
import io.tdd.lectureservice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> searchAllByUserIdOrderById(Long userId);

    Optional<Reservation> findByUserAndLecture(User user, Lecture lecture);
}
