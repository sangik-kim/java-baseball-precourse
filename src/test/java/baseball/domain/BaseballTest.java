package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Baseball test")
public class BaseballTest {

    private Baseball baseball;
    private BaseballGameConfiguration baseballGameConfiguration;

    @DisplayName("pitch method")
    @Nested
    class Describe_pitch {

        @BeforeEach
        void setUp() {
            baseballGameConfiguration = BaseballGameConfiguration.defaultConfiguration();
            baseball = Baseball.create(baseballGameConfiguration);
        }


        @DisplayName("creates the configured number of pitches")
        @Test
        void it_returns_true() {
            baseball.pitch();

            Map<BallIndex, BallNumber> pitchedNumbers = baseball.getPitchedNumbers();
            assertThat(pitchedNumbers).hasSize(baseballGameConfiguration.getPitchCount());
        }
    }

    @DisplayName("getPitchedBallNumbers method")
    @Nested
    class Describe_getPitchedBallNumbers {

        @BeforeEach
        void setUp() {
            baseballGameConfiguration = BaseballGameConfiguration.defaultConfiguration();
            baseball = Baseball.create(baseballGameConfiguration);
            baseball.pitch();
        }

        @DisplayName("creates the configured number of pitches")
        @Test
        void it_returns_true() {
            String pitchedBallNumbers = baseball.getPitchedBallNumbers();
            assertThat(pitchedBallNumbers).isNotNull().isNotEmpty().hasSize(baseballGameConfiguration.getPitchCount());
        }
    }

    @DisplayName("getResult method")
    @Nested
    class Describe_getResult {

        private UserPrediction userPrediction;

        @BeforeEach
        void setUp() {
            Map<BallIndex, BallNumber> pitchedNumbers = new LinkedHashMap<>();
            pitchedNumbers.put(BallIndex.FIRST, BallNumber.from(4));
            pitchedNumbers.put(BallIndex.SECOND, BallNumber.from(2));
            pitchedNumbers.put(BallIndex.THIRD, BallNumber.from(5));

            baseballGameConfiguration = BaseballGameConfiguration.defaultConfiguration();
            baseball = new Baseball(pitchedNumbers, baseballGameConfiguration);
        }

        @DisplayName("with strike value")
        @Nested
        class Context_with_strike_value {

            private BaseballResult expected;

            @BeforeEach
            void setUp() {
                List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(1), BallNumber.from(2),
                        BallNumber.from(3));
                userPrediction = UserPrediction.from(ballNumberList);

                expected = new BaseballResult(1, 0, baseballGameConfiguration);
            }

            @DisplayName("returns one strike")
            @Test
            void it_returns_one_strike() {
                BaseballResult result = baseball.getResult(userPrediction);
                assertThat(result).isEqualTo(expected);
            }
        }

        @DisplayName("with ball and strike value")
        @Nested
        class Context_with_ball_and_strike_value {

            private BaseballResult expected;

            @BeforeEach
            void setUp() {
                List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(4), BallNumber.from(5),
                        BallNumber.from(6));
                userPrediction = UserPrediction.from(ballNumberList);

                expected = new BaseballResult(1, 1, baseballGameConfiguration);
            }

            @DisplayName("returns one ball and one strike")
            @Test
            void it_returns_one_ball_and_one_strike() {
                BaseballResult result = baseball.getResult(userPrediction);
                assertThat(result).isEqualTo(expected);
            }
        }

        @DisplayName("with nothing value")
        @Nested
        class Context_with_nothing_value {

            private BaseballResult expected;

            @BeforeEach
            void setUp() {
                List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(7), BallNumber.from(8),
                        BallNumber.from(9));
                userPrediction = UserPrediction.from(ballNumberList);

                expected = new BaseballResult(0, 0, baseballGameConfiguration);
            }

            @DisplayName("returns nothing")
            @Test
            void it_returns_one_ball_and_one_strike() {
                BaseballResult result = baseball.getResult(userPrediction);
                assertThat(result).isEqualTo(expected);
            }
        }
    }
}
