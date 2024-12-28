package io.tdd.lectureservice.lecture.domain;

import io.tdd.lectureservice.lecture.interfaces.LectureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Test
    @DisplayName("특강 신청 목록을 조회할 수 있다")
    void findAllByLectureDate() throws ParseException {
        // given
        LocalDate now = LocalDate.now();
        LocalDateTime startDate = now.atStartOfDay();
        LocalDateTime endDate = now.atTime(LocalTime.MAX);

        when(lectureRepository.findAllByLectureDateBetween(startDate, endDate)).thenReturn((List.of(new Lecture(1L, "특강", 30, "허재", LocalDateTime.now()))));

        // when, then
        assertThat(lectureService.search(now).size()).isEqualTo(1);
    }

}
