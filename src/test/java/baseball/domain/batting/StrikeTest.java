package baseball.domain.batting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Strike test")
public class StrikeTest {

    @DisplayName("check type if it's Strike")
    @Test
    void it_is_strike_type() {
        Strike strike = Strike.create();
        assertThat(strike.getResult()).isEqualTo(BattingResultType.STRIKE);
    }
}
