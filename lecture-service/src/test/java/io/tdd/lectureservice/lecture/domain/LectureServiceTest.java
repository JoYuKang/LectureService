package io.tdd.lectureservice.lecture.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

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
        when(lectureRepository.findAllByLectureDate(LocalDate.now())).thenReturn((List.of(new Lecture(1L, "특강", 30, "허재", LocalDate.now()))));

        // when, then
        assertThat(lectureService.search(LocalDate.now()).size()).isEqualTo(1);
    }



}