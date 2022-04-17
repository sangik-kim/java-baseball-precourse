package baseball.domain;

import baseball.domain.batting.BattingResult;
import baseball.domain.batting.BattingResultType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BaseballResult {

    private final int strike;
    private final int ball;
    private final BaseballGameConfiguration configuration;

    private BaseballResult(BaseballGameConfiguration configuration) {
        strike = 0;
        ball = 0;
        this.configuration = configuration;
    }

    public BaseballResult(int strike, int ball, BaseballGameConfiguration configuration) {
        this.strike = strike;
        this.ball = ball;
        this.configuration = configuration;
    }

    public static BaseballResult create(BaseballGameConfiguration configuration) {
        return new BaseballResult(configuration);
    }

    /**
     * create a new BaseballResult after adding the given BaseballResult
     *
     * @param battingResult BaseballResult to add
     * @return BaseballResult new instance including the new given BattingResult
     */
    public BaseballResult add(BattingResult battingResult) {
        return battingResult.getResult() == BattingResultType.STRIKE ? addStrike() : processBall(battingResult);
    }

    /**
     * check if user won
     *
     * @return boolean if won
     */
    public boolean isWon() {
        return strike == configuration.getPitchCount();
    }

    /**
     * return the result string to show user
     *
     * @return String result string
     */
    public String getResultString() {
        final String ballStrikeString = String.join(" ", getBallString(), getStrikeString()).trim();

        return ballStrikeString.isEmpty() ? getNothingString() + "\n" : ballStrikeString + "\n";
    }

    private String getStrikeString() {
        return strike > 0 ? String.format("%d%s", strike, BattingResultType.STRIKE.getType()) : "";
    }

    private String getBallString() {
        return ball > 0 ? String.format("%d%s", ball, BattingResultType.BALL.getType()) : "";
    }

    private String getNothingString() {
        return BattingResultType.NOTHING.getType();
    }

    private BaseballResult processBall(BattingResult battingResult) {
        return battingResult.getResult() == BattingResultType.BALL ? addBall() : addNothing();
    }

    private BaseballResult addStrike() {
        return new BaseballResult(strike + 1, ball, configuration);
    }

    private BaseballResult addBall() {
        return new BaseballResult(strike, ball + 1, configuration);
    }

    private BaseballResult addNothing() {
        return this;
    }
}
