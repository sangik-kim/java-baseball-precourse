package baseball.domain.batting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Ball test")
public class BallTest {

    @DisplayName("check type if it's Ball")
    @Test
    void it_is_ball_type() {
        Ball ball = Ball.create();
        assertThat(ball.getResult()).isEqualTo(BattingResultType.BALL);
    }
}
