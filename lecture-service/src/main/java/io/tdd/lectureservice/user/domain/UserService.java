package io.tdd.lectureservice.user.domain;

import io.tdd.lectureservice.support.common.validation.Validation;
import io.tdd.lectureservice.support.exception.CustomNotFoundException;
import io.tdd.lectureservice.support.exception.InvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 특정 유저 조회
     * @param userId 조회할 유저의 ID
     * @return user
     * @throws InvalidException userId가 유효하지 않은 경우
     * @throws CustomNotFoundException 유저를 찾지 못한 경우
     */
    public User getUser(Long userId) {
        Validation.checkId("유저", userId);

        return userRepository.findById(userId).orElseThrow(() ->
                new CustomNotFoundException("유저"));
    }

}
