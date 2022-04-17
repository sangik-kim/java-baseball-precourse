package baseball.domain.batting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Nothing test")
public class NothingTest {

    @DisplayName("check type if it's Nothing")
    @Test
    void it_is_nothing_type() {
        Nothing nothing = Nothing.create();
        assertThat(nothing.getResult()).isEqualTo(BattingResultType.NOTHING);
    }
}
