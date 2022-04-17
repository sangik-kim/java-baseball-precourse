package baseball.domain.batting;

public interface BattingResult {
    /**
     * return the batting result type (BALL, STRIKE, NOTHING)
     *
     * @return BattingResultType
     */
    BattingResultType getResult();
}
