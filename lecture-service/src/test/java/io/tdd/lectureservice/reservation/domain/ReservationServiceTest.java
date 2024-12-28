package io.tdd.lectureservice.reservation.domain;

import io.tdd.lectureservice.lecture.domain.Lecture;
import io.tdd.lectureservice.lecture.interfaces.LectureRepository;
import io.tdd.lectureservice.reservation.interfaces.ReservationRepository;
import io.tdd.lectureservice.reservation.interfaces.dto.request.ReservationRequest;
import io.tdd.lectureservice.support.exception.CustomNotFoundException;
import io.tdd.lectureservice.support.exception.MaxCapacityException;
import io.tdd.lectureservice.user.domain.User;
import io.tdd.lectureservice.user.interfaces.UserRepository;
import io.tdd.lectureservice.user.domain.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;
    @InjectMocks
    private UserService userService;

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private LectureRepository lectureRepository;

    @Test
    @DisplayName("수강 예약 신청할 수 있다.")
    void reserve() {
        // given
        User user = new User(1L, "김하나");
        Lecture lecture = new Lecture(1L, "TDD", 30, "허재", LocalDateTime.now());
        Reservation reservation = new Reservation(1L, user, lecture, lecture.getSubject(), lecture.getSpeakerName(), lecture.getLectureDate());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(lectureRepository.findById(1L)).thenReturn(Optional.of(lecture));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        ReservationRequest request = new ReservationRequest(user.getId(), lecture.getId());

        // when, then
        assertThat(reservationService.add(request).getId()).isEqualTo(1L);
        assertThat(reservationService.add(request).getSubject()).isEqualTo(lecture.getSubject());
    }

    @Test
    @DisplayName("수강생이 30명이 넘은 특강을 예약 신청 시 해당 요청은 실패한다.")
    void failedWhenLectureCountExceeds30() {
        // given
        User user = new User(1L, "김하나");
        Lecture lecture = new Lecture(1L, "TDD", 0, "허재", LocalDateTime.now());
        Reservation reservation = new Reservation(1L, user, lecture, lecture.getSubject(), lecture.getSpeakerName(), lecture.getLectureDate());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(lectureRepository.findById(1L)).thenReturn(Optional.of(lecture));

        // when, then
        ReservationRequest request = new ReservationRequest(user.getId(), lecture.getId());
        assertThatThrownBy(() -> reservationService.add(request)).isInstanceOf(MaxCapacityException.class);

    }

    @Test
    @DisplayName("특정 유저의 특강 신청내역을 조회할 수 있다.")
    void showReserve() {
        // given
        User user = new User(1L, "김하나");
        Lecture lecture1 = new Lecture(1L, "TDD", 30, "허재", LocalDateTime.now());
        Lecture lecture2 = new Lecture(2L, "Clean 아키텍처", 30, "허재", LocalDateTime.now());
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        List<Reservation> reservationList = List.of(
                new Reservation(1L, user, lecture1, lecture1.getSubject(), lecture1.getSpeakerName(), lecture1.getLectureDate()),
                new Reservation(2L, user, lecture2, lecture2.getSubject(), lecture2.getSpeakerName(), lecture2.getLectureDate())
        );

        userRepository.save(user);
        when(reservationRepository.searchAllByUserIdOrderById(user.getId())).thenReturn(reservationList);

        // when, then
        assertThat(reservationService.reserveList(user.getId()).size()).isEqualTo(2);

    }

    @Test
    @DisplayName("유저가 존재하지 않는다면 신청 시 실패한다.")
    void failedUserNotFound() {

        // when, then
        assertThatThrownBy(() -> reservationService.reserveList(1L)).isInstanceOf(CustomNotFoundException.class);

    }

    @Test
    @DisplayName("특강이 존재하지 않는다면 신청 시 실패한다.")
    void failedLectureNotFound() {
        // given
        User user = new User(1L, "김하나");
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userRepository.save(user);
        ReservationRequest request = new ReservationRequest(user.getId(), 1L);

        // when, then
        assertThatThrownBy(() -> reservationService.add(request)).isInstanceOf(CustomNotFoundException.class);

    }
}
