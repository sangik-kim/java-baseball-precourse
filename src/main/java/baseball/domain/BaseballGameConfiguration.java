package baseball.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class BaseballGameConfiguration {
    private static final int MINIMUM_BALL_NUMBER = 1;
    private static final int MAXIMUM_BALL_NUMBER = 9;
    private static final int PITCH_COUNT = 3;

    private final int minimumBallNumber;
    private final int maximumBallNumber;
    private final int pitchCount;

    private BaseballGameConfiguration(int minimumBallNumber, int maximumBallNumber, int pitchCount) {
        this.minimumBallNumber = minimumBallNumber;
        this.maximumBallNumber = maximumBallNumber;
        this.pitchCount = pitchCount;
    }

    public static BaseballGameConfiguration of(int minimumBallNumber, int maximumBallNumber, int pitchCount) {
        return new BaseballGameConfiguration(minimumBallNumber, maximumBallNumber, pitchCount);
    }

    public static BaseballGameConfiguration defaultConfiguration() {
        return of(MINIMUM_BALL_NUMBER, MAXIMUM_BALL_NUMBER, PITCH_COUNT);
    }
}
