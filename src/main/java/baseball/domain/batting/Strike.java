package baseball.domain.batting;

public class Strike implements BattingResult {

    private Strike() {
    }

    public static Strike create() {
        return new Strike();
    }

    @Override
    public BattingResultType getResult() {
        return BattingResultType.STRIKE;
    }
}
