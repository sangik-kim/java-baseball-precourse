package baseball.domain.batting;

public class Nothing implements BattingResult {

    private Nothing() {
    }

    public static Nothing create() {
        return new Nothing();
    }

    @Override
    public BattingResultType getResult() {
        return BattingResultType.NOTHING;
    }
}
