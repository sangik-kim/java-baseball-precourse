package baseball.domain;

import baseball.domain.batting.Ball;
import baseball.domain.batting.BattingResult;
import baseball.domain.batting.Nothing;
import baseball.domain.batting.Strike;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;

public class Baseball {

    @Getter
    private final Map<BallIndex, BallNumber> pitchedNumbers;
    private final BaseballGameConfiguration configuration;

    public Baseball(Map<BallIndex, BallNumber> pitchedNumbers, BaseballGameConfiguration configuration) {
        this.pitchedNumbers = pitchedNumbers;
        this.configuration = configuration;
    }

    /**
     * create an empty Baseball instance
     *
     * @return an empty Baseball instance
     */
    public static Baseball create(BaseballGameConfiguration configuration) {
        return new Baseball(new LinkedHashMap<>(), configuration);
    }

    /**
     * generate three random numbers as pitching balls
     */
    public void pitch() {
        if (pitchedNumbers.keySet().size() == configuration.getPitchCount()) {
            return;
        }
        pitch(BallIndex.FIRST);
        pitch(BallIndex.SECOND);
        pitch(BallIndex.THIRD);
    }

    /**
     * get the game result for the given user's prediction
     *
     * @param userPrediction user's prediction of ball numbers
     * @return BaseballResult baseball game result
     */
    public BaseballResult getResult(UserPrediction userPrediction) {
        userPrediction.validate(configuration);
        BallNumber[] prediction = userPrediction.getArray();
        BaseballResult baseballResult = BaseballResult.create(configuration);
        for (int i = 0; i < configuration.getPitchCount(); i++) {
            baseballResult = baseballResult.add(compareBallNumber(BallIndex.findByValue(i), prediction[i]));
        }
        return baseballResult;
    }

    /**
     * get pitched ball numbers
     *
     * @return String pitched ball numbers
     */
    public String getPitchedBallNumbers() {
        StringBuilder ballNumbers = new StringBuilder();
        pitchedNumbers.entrySet().forEach(e -> ballNumbers.append(e.getValue()));
        return ballNumbers.toString();
    }

    private BattingResult compareBallNumber(BallIndex ballIndex, BallNumber ballNumber) {
        return pitchedNumbers.get(ballIndex).equals(ballNumber) ? getStrike() : processBall(ballNumber);
    }

    private BattingResult processBall(BallNumber ballNumber) {
        return pitchedNumbers.containsValue(ballNumber) ? getBall() : getNothing();
    }

    private BattingResult getStrike() {
        return Strike.create();
    }

    private BattingResult getBall() {
        return Ball.create();
    }

    private BattingResult getNothing() {
        return Nothing.create();
    }

    private void pitch(BallIndex index) {
        pitchedNumbers.put(index, getBallNumber());
    }

    private BallNumber getBallNumber() {
        BallNumber ballNumber = BallNumber.from(
                Randoms.pickNumberInRange(configuration.getMinimumBallNumber(), configuration.getMaximumBallNumber()));
        return pitchedNumbers.containsValue(ballNumber) ? getBallNumber() : ballNumber;
    }
}
