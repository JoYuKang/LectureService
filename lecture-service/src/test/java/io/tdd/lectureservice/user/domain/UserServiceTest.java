package io.tdd.lectureservice.user.domain;

import io.tdd.lectureservice.support.exception.CustomNotFoundException;
import io.tdd.lectureservice.support.exception.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("유저를 조회할 수 있다.")
    void create() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "하늘")));

        // when, then
        assertThat((userService.getUser(1L)).getName()).isEqualTo("하늘");
    }

    @Test
    @DisplayName("유저 존재하지 않으면 유저 조회에 실패한다.")
    void failedUserNotFound() {
        // when, then
        assertThatThrownBy(() -> userService.getUser(1L)).isInstanceOf(CustomNotFoundException.class);
    }

    @Test
    @DisplayName("유효하지 않은 유저 ID가 입력되면 유저 조회에 실패한다.")
    void failedInvalidUserId() {
        // when, then
        assertThatThrownBy(() -> userService.getUser(-1L)).isInstanceOf(InvalidException.class);
    }
}
