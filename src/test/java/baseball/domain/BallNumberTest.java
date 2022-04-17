package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BallNumber test")
public class BallNumberTest {

    @DisplayName("test equality")
    @Nested
    class Describe_equality {

        private BallNumber ballNumber1;
        private BallNumber ballNumber2;

        @DisplayName("with same value")
        @Nested
        class Context_with_same_value {

            private final int value = 5;

            @BeforeEach
            void setUp() {
                ballNumber1 = BallNumber.from(value);
                ballNumber2 = BallNumber.from(value);
            }

            @DisplayName("returns true")
            @Test
            void it_returns_true() {
                boolean equality = ballNumber1.equals(ballNumber2);
                assertThat(equality).isTrue();
            }
        }

        @DisplayName("with different values")
        @Nested
        class Context_with_different_values {

            private final int value1 = 2;
            private final int value2 = 5;

            @BeforeEach
            void setUp() {
                ballNumber1 = BallNumber.from(value1);
                ballNumber2 = BallNumber.from(value2);
            }

            @DisplayName("returns false")
            @Test
            void it_returns_false() {
                boolean equality = ballNumber1.equals(ballNumber2);
                assertThat(equality).isFalse();
            }
        }
    }
}
