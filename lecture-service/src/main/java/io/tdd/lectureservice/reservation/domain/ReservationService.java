package io.tdd.lectureservice.reservation.domain;

import io.tdd.lectureservice.lecture.domain.Lecture;
import io.tdd.lectureservice.lecture.domain.LectureRepository;
import io.tdd.lectureservice.reservation.interfaces.dto.request.ReservationRequest;
import io.tdd.lectureservice.reservation.interfaces.dto.response.ReservationResponse;
import io.tdd.lectureservice.support.exception.CustomNotFoundException;
import io.tdd.lectureservice.support.exception.DuplicateException;
import io.tdd.lectureservice.support.exception.InvalidException;
import io.tdd.lectureservice.support.exception.MaxCapacityException;
import io.tdd.lectureservice.user.domain.User;
import io.tdd.lectureservice.user.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;


    /**
     * 유저의 특강 예약
     * @param request 조회할 유저의 ID, 특강의 ID를 가진 요청
     * @return ReservationResponse
     * @throws InvalidException userId, lectureId가 유효하지 않은 경우
     * @throws CustomNotFoundException 유저, 특강를 찾지 못한 경우
     * @throws CustomNotFoundException 유저, 특강를 찾지 못한 경우
     * @throws DuplicateException      동일한 유저가 동일한 특강에 동일 요청을 보낼 경우
     * @throws MaxCapacityException    수강생이 30명이 넘을 경우
     */
    @Transactional
    public ReservationResponse add(ReservationRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new CustomNotFoundException("유저"));
        Lecture lecture = lectureRepository.findById(request.getLectureId()).orElseThrow(() -> new CustomNotFoundException("특강"));
        reservationRepository.findByUserAndLecture(user, lecture)
                .ifPresent(reservation -> {
                    throw new DuplicateException("이미 해당 강의에 대한 예약이 존재합니다.");
                });
        if (lecture.getCapacity() <= 0) {
            throw new MaxCapacityException();
        }
        Reservation reservation = new Reservation(user, lecture);
        Reservation saveReservation = reservationRepository.save(reservation);
        lecture.countdown();
        lectureRepository.save(lecture);

        // 예약 저장 및 return
        return new ReservationResponse(saveReservation);
    }

    /**
     * 특정 유저의 특강 신청 완료 목록 조회
     * @param  userId 조회할 유저의 ID
     * @return List ReservationResponse
     * @throws InvalidException userId가 유효하지 않은 경우
     * @throws CustomNotFoundException 유저를 찾지 못한 경우
     */
    public List<ReservationResponse> reserveList(long userId) {
        userRepository.findById(userId).orElseThrow(() -> new CustomNotFoundException("유저"));
        return reservationRepository.searchAllByUserIdOrderById(userId).stream().map(ReservationResponse::new).collect(Collectors.toList());
    }



}
