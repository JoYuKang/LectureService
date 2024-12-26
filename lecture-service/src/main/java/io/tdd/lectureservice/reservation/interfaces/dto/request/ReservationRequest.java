package io.tdd.lectureservice.reservation.interfaces.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    @NotBlank(message = "유저 ID는 필수 입력값입니다.")
    @Min(value = 1, message = "userId의 값이 요휴하지 않습니다.")
    private Long userId;

    @NotBlank(message = "특강 ID는 필수 입력값입니다.")
    @Min(value = 1, message = "lectureId의 값이 요휴하지 않습니다.")
    private Long lectureId;

}
