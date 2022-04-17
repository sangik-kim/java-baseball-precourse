package baseball.domain;

import baseball.dto.UserBallNumbers;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BallNumber {
    private final Integer value;

    private BallNumber(Integer value) {
        this.value = value;
    }

    /**
     * generate BallNumber instance with the given value
     *
     * @param value integer value
     * @return BallNubmer instance
     */
    public static BallNumber from(int value) {
        return new BallNumber(value);
    }

    public static List<BallNumber> from(UserBallNumbers userBallNumbers) {
        List<BallNumber> ballNumberList = new ArrayList<>();
        userBallNumbers.getBallNumbers().forEach(n -> ballNumberList.add(BallNumber.from(n)));
        return ballNumberList;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
