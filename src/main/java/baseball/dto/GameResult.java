package baseball.dto;

import baseball.domain.BaseballResult;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class GameResult {
    private final Boolean isWon;
    private final String message;

    private GameResult(Boolean isWon, String message) {
        this.isWon = isWon;
        this.message = message;
    }

    public static GameResult of(Boolean isWon, String message) {
        return new GameResult(isWon, message);
    }

    public static GameResult from(BaseballResult baseballResult) {
        return of(baseballResult.isWon(), baseballResult.getResultString());
    }
}
