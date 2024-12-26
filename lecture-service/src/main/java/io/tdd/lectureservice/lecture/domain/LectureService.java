package io.tdd.lectureservice.lecture.domain;

import io.tdd.lectureservice.lecture.interfaces.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    /**
     * 특정 날짜의 특강 리스트를 조회
     * 유저는 특강에 신청 전 목록을 조회
     * @param date 조회할 날짜
     * @return lecture dto list
     */
    public List<LectureResponse> search(LocalDate date) {
        // 주어진 날짜를 해당 날짜의 시작 시간과 끝 시간 설정
        LocalDateTime startDate = date.now().atStartOfDay();
        LocalDateTime endDate = date.now().atTime(LocalTime.MAX);
        return lectureRepository.findAllByLectureDateBetween(startDate, endDate).stream().map(LectureResponse::new).collect(Collectors.toList());
    }

}
