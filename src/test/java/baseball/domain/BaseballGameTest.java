package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import baseball.dto.GameResult;
import baseball.dto.UserBallNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BaseballGame test")
public class BaseballGameTest {

    private BaseballGame baseballGame;
    private Baseball computer;

    @BeforeEach
    void setUp() {
        computer = mock(Baseball.class);
        baseballGame = new BaseballGame(computer);
    }

    @DisplayName("start method")
    @Nested
    class Describe_start {

        @BeforeEach
        void setUp() {
            doNothing().when(computer).pitch();
        }

        @DisplayName("execute pitch method")
        @Test
        void it_executes_pitch() {
            baseballGame.start();
            verify(computer, times(1)).pitch();
        }
    }

    @DisplayName("play method")
    @Nested
    class Describe_play {

        private GameResult expected;

        @BeforeEach
        void setUp() {
            BaseballResult result = new BaseballResult(3, 0, BaseballGameConfiguration.defaultConfiguration());
            when(computer.getResult(any(UserPrediction.class))).thenReturn(result);

            expected = GameResult.of(Boolean.TRUE, "3스트라이크\n");
        }

        @DisplayName("returns the same with expected")
        @Test
        void it_returns_the_same_object_with_expected() {
            GameResult actual = baseballGame.play(UserBallNumbers.from("123"));
            assertThat(actual).isEqualTo(expected);
        }
    }

    @DisplayName("getComputerNumber method")
    @Nested
    class Describe_getComputerNumber {

        private String expected;

        @BeforeEach
        void setUp() {
            expected = "123";
            when(computer.getPitchedBallNumbers()).thenReturn(expected);
        }

        @DisplayName("returns the same with expected")
        @Test
        void it_returns_the_same_object_with_expected() {
            String actual = baseballGame.getComputerNumber();
            assertThat(actual).isEqualTo(expected);
        }
    }
}
