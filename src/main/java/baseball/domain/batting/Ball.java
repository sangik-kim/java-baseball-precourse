package baseball.domain.batting;

public class Ball implements BattingResult {

    private Ball() {
    }

    public static Ball create() {
        return new Ball();
    }

    @Override
    public BattingResultType getResult() {
        return BattingResultType.BALL;
    }
}
