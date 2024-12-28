package io.tdd.lectureservice.lecture.interfaces;

import io.tdd.lectureservice.lecture.domain.LectureService;
import io.tdd.lectureservice.lecture.interfaces.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    // 특강 신청 가능 목록 조회 API
    @GetMapping("/list/{date}")
    public List<LectureResponse> getLectureList(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate date) {
        return lectureService.search(date).stream().map(LectureResponse::new).collect(Collectors.toList());
    }

}
