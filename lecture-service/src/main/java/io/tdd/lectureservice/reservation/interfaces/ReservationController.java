package io.tdd.lectureservice.reservation.interfaces;

import io.tdd.lectureservice.reservation.domain.ReservationService;
import io.tdd.lectureservice.reservation.interfaces.dto.request.ReservationRequest;
import io.tdd.lectureservice.reservation.interfaces.dto.response.ReservationResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReservationController {

    private final ReservationService reservationService;

    // 특강 신청 완료 목록 조회 API
    @GetMapping("/list/{userId}")
    public List<ReservationResponse> getreservationList(@Min(1) @PathVariable Long userId) {
        return reservationService.reserveList(userId).stream().map(ReservationResponse::new).collect(Collectors.toList());
    }

    // 특강 신청 API
    @PostMapping("/add")
    public ReservationResponse addReservation(@RequestBody ReservationRequest request) {
        return new ReservationResponse(reservationService.add(request));
    }
}
