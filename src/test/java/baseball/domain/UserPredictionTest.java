package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("UserPrediction test")
public class UserPredictionTest {

    private UserPrediction userPrediction;

    @DisplayName("getArray method")
    @Nested
    class Describe_getArray {

        private BallNumber[] expected;

        @BeforeEach
        void setUp() {
            List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(1), BallNumber.from(2), BallNumber.from(3));
            userPrediction = UserPrediction.from(ballNumberList);

            expected = new BallNumber[]{BallNumber.from(1), BallNumber.from(2), BallNumber.from(3)};
        }

        @DisplayName("creates an array with same ball numbers")
        @Test
        void it_returns_true() {
            BallNumber[] ballNumbers = userPrediction.getArray();

            assertThat(ballNumbers).containsExactlyInAnyOrder(expected);
        }
    }

    @DisplayName("validate method")
    @Nested
    class Describe_validate {

        private BaseballGameConfiguration configuration;

        @BeforeEach
        void setUp() {
            configuration = BaseballGameConfiguration.defaultConfiguration();
        }

        @DisplayName("with short value")
        @Nested
        class Context_with_short_value {

            @BeforeEach
            void setUp() {
                List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(1), BallNumber.from(2));
                userPrediction = UserPrediction.from(ballNumberList);
            }

            @DisplayName("throws IllegalArgumentException")
            @Test
            void it_returns_one_strike() {
                assertThatThrownBy(() -> userPrediction.validate(configuration))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @DisplayName("with duplicates")
        @Nested
        class Context_with_duplicated_numbers {

            @BeforeEach
            void setUp() {
                List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(1), BallNumber.from(2),
                        BallNumber.from(1));
                userPrediction = UserPrediction.from(ballNumberList);
            }

            @DisplayName("throws IllegalArgumentException")
            @Test
            void it_returns_one_strike() {
                assertThatThrownBy(() -> userPrediction.validate(configuration))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @DisplayName("with smaller than min")
        @Nested
        class Context_with_smaller_number {

            @BeforeEach
            void setUp() {
                List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(0), BallNumber.from(2),
                        BallNumber.from(1));
                userPrediction = UserPrediction.from(ballNumberList);
            }

            @DisplayName("throws IllegalArgumentException")
            @Test
            void it_returns_one_strike() {
                assertThatThrownBy(() -> userPrediction.validate(configuration))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @DisplayName("with bigger than max")
        @Nested
        class Context_with_bigger_number {

            @BeforeEach
            void setUp() {
                List<BallNumber> ballNumberList = Arrays.asList(BallNumber.from(1), BallNumber.from(2),
                        BallNumber.from(10));
                userPrediction = UserPrediction.from(ballNumberList);
            }

            @DisplayName("throws IllegalArgumentException")
            @Test
            void it_returns_one_strike() {
                assertThatThrownBy(() -> userPrediction.validate(configuration))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }
}
