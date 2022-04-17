package baseball.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UserPrediction {

    private final List<BallNumber> ballNumbers;

    public UserPrediction(List<BallNumber> ballNumbers) {
        this.ballNumbers = ballNumbers;
    }

    public static UserPrediction from(List<BallNumber> ballNumbers) {
        return new UserPrediction(ballNumbers);
    }

    /**
     * convert to array
     *
     * @return BallNumber[]
     */
    public BallNumber[] getArray() {
        return ballNumbers.toArray(new BallNumber[ballNumbers.size()]);
    }

    /**
     * validate the user prediction data (length, duplicates, min and max)
     *
     * @param configuration BaseballGameConfiguration
     * @throw IllegalArgumentException if it's invalid
     */
    public void validate(BaseballGameConfiguration configuration) {
        checkDuplicate();
        checkLength(configuration);
        ballNumbers.forEach(n -> checkMinAndMax(n, configuration));
    }

    private void checkLength(BaseballGameConfiguration configuration) {
        if (ballNumbers.size() != configuration.getPitchCount()) {
            throw new IllegalArgumentException(configuration.getPitchCount() + "자리 숫자만 허용됩니다!");
        }
    }

    private void checkDuplicate() {
        Set<BallNumber> s = new LinkedHashSet<>();
        ballNumbers.forEach(s::add);
        if (s.size() != ballNumbers.size()) {
            throw new IllegalArgumentException("중복된 숫자를 입력할 수 없습니다!");
        }
    }

    private void checkMinAndMax(BallNumber ballNumber, BaseballGameConfiguration configuration) {
        checkMinimumNumber(ballNumber, configuration);
        checkMaximumNumber(ballNumber, configuration);
    }

    private void checkMinimumNumber(BallNumber ballNumber, BaseballGameConfiguration configuration) {
        if (ballNumber.getValue() < configuration.getMinimumBallNumber()) {
            throw new IllegalArgumentException(
                    String.format("최소 %d이상의 숫자를 입력해야합니다!", configuration.getMinimumBallNumber()));
        }
    }

    private void checkMaximumNumber(BallNumber ballNumber, BaseballGameConfiguration configuration) {
        if (ballNumber.getValue() > configuration.getMaximumBallNumber()) {
            throw new IllegalArgumentException(
                    String.format("최대 %d이하의 숫자를 입력해야합니다!", configuration.getMaximumBallNumber()));
        }
    }
}
