package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.domain.batting.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BaseballResult test")
public class BaseballResultTest {

    private static final BaseballGameConfiguration CONFIGURATION = BaseballGameConfiguration.defaultConfiguration();

    private BaseballResult baseballResult;


    @DisplayName("add method")
    @Nested
    class Describe_add {

        private BaseballResult expected;

        @BeforeEach
        void setUp() {
            baseballResult = new BaseballResult(0, 0, CONFIGURATION);
            expected = new BaseballResult(1, 0, CONFIGURATION);
        }

        @DisplayName("creates a new instance with strike")
        @Test
        void it_creates_a_new_instance_with_strike() {
            BaseballResult newBaseballResult = baseballResult.add(Strike.create());
            assertThat(newBaseballResult).isEqualTo(expected);
        }
    }

    @DisplayName("isWon method")
    @Nested
    class Describe_isWon {

        @DisplayName("with won value")
        @Nested
        class Context_with_won_value {

            @BeforeEach
            void setUp() {
                baseballResult = new BaseballResult(3, 0, CONFIGURATION);
            }


            @DisplayName("returns true")
            @Test
            void it_returns_true() {
                assertThat(baseballResult.isWon()).isTrue();
            }
        }

        @DisplayName("with no won value")
        @Nested
        class Context_with_no_won_value {

            @BeforeEach
            void setUp() {
                baseballResult = new BaseballResult(0, 3, CONFIGURATION);
            }

            @DisplayName("returns false")
            @Test
            void it_returns_false() {
                assertThat(baseballResult.isWon()).isFalse();
            }
        }
    }

    @DisplayName("getResultString method")
    @Nested
    class Describe_getResultString {

        @DisplayName("with strike value")
        @Nested
        class Context_with_strike_value {

            @BeforeEach
            void setUp() {
                baseballResult = new BaseballResult(2, 0, CONFIGURATION);
            }


            @DisplayName("returns true")
            @Test
            void it_returns_true() {
                assertThat(baseballResult.getResultString()).matches("2스트라이크\n");
            }
        }

        @DisplayName("with ball value")
        @Nested
        class Context_with_ball_value {

            @BeforeEach
            void setUp() {
                baseballResult = new BaseballResult(0, 1, CONFIGURATION);
            }


            @DisplayName("returns true")
            @Test
            void it_returns_true() {
                assertThat(baseballResult.getResultString()).matches("1볼\n");
            }
        }

        @DisplayName("with ball and strike value")
        @Nested
        class Context_with_ball_and_strike_value {

            @BeforeEach
            void setUp() {
                baseballResult = new BaseballResult(1, 1, CONFIGURATION);
            }


            @DisplayName("returns true")
            @Test
            void it_returns_true() {
                assertThat(baseballResult.getResultString()).matches("1볼 1스트라이크\n");
            }
        }

        @DisplayName("with nothing value")
        @Nested
        class Context_with_nothing_value {

            @BeforeEach
            void setUp() {
                baseballResult = new BaseballResult(0, 0, CONFIGURATION);
            }


            @DisplayName("returns true")
            @Test
            void it_returns_true() {
                assertThat(baseballResult.getResultString()).matches("낫싱\n");
            }
        }
    }
}
