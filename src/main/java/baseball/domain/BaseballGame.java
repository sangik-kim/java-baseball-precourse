package baseball.domain;

import baseball.dto.GameResult;
import baseball.dto.UserBallNumbers;

public class BaseballGame {
    private Baseball computer;

    public BaseballGame(Baseball computer) {
        this.computer = computer;
    }

    /**
     * start a new baseball game with the computer
     */
    public void start() {
        computer.pitch();
    }

    /**
     * check user's input
     *
     * @param userBallNumbers
     * @return GameResult check result
     */
    public GameResult play(UserBallNumbers userBallNumbers) {
        UserPrediction userPrediction = UserPrediction.from(BallNumber.from(userBallNumbers));
        BaseballResult result = computer.getResult(userPrediction);
        return GameResult.from(result);
    }

    /**
     * get computer's number
     * @return String computer's number
     */
    public String getComputerNumber() {
        return computer.getPitchedBallNumbers();
    }
}
