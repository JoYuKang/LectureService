package io.tdd.lectureservice.reservation.domain;


import io.tdd.lectureservice.lecture.domain.Lecture;
import io.tdd.lectureservice.lecture.interfaces.LectureRepository;
import io.tdd.lectureservice.reservation.interfaces.dto.request.ReservationRequest;
import io.tdd.lectureservice.user.domain.User;
import io.tdd.lectureservice.user.interfaces.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class ReservationIntegrationTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;


    @Test
    @DisplayName("동시에 40명의 유저가 수강신청 시 30명만 수강신청에 성공한다.")
    void failedOverCapacityLecture() throws InterruptedException {
        // given
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(40);
        AtomicInteger successfulReservations = new AtomicInteger(0);
        AtomicInteger failedReservations = new AtomicInteger(0);

        // 사용자 40명 생성 및 저장
        List<User> users = IntStream.rangeClosed(1, 40)
                .mapToObj(i -> new User(null, "user name " + i))
                .collect(Collectors.toList());
        userRepository.saveAll(users);

        // 강의 생성 및 저장
        Lecture lecture = lectureRepository.save(new Lecture(null, "lecture 1", 30, "김강사", LocalDateTime.now()));

        // when 40명의 사용자로부터 수강 신청
        for (User user : users) {
            executor.submit(() -> {
                try {
                    ReservationRequest request = new ReservationRequest(user.getId(), lecture.getId());
                    reservationService.add(request);
                    successfulReservations.incrementAndGet();
                } catch (Exception e) {
                    System.out.println("err >> " + e.getMessage());
                    failedReservations.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
        System.out.println("successfulReservations >> " + successfulReservations.get());
        System.out.println("failedReservations >> " + failedReservations.get());
        // then 성공 및 실패 수 확인
        assertThat(successfulReservations.get()).isEqualTo(30);
        assertThat(failedReservations.get()).isEqualTo(10);
    }

    @Test
    @DisplayName("일한 유저 정보로 같은 특강을 5번 신청했을 때, 1번만 성공하고 나머지는 실패한다.")
    void failedDoubleAddLecture1() throws InterruptedException {

        // given
        User user = userRepository.save(new User(null, "user name 1"));
        Lecture lecture = lectureRepository.save(new Lecture(null, "lecture 1", 30, "김강사", LocalDateTime.now()));

        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(5);

        AtomicInteger successCount= new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        //when
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                try {
                    reservationService.add(new ReservationRequest(user.getId(), lecture.getId()));
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        // then
        assertThat(successCount.get()).isEqualTo(1); // 성공한 신청
        assertThat(failCount.get()).isEqualTo(4); // 실패한 신청
    }

}
