package baseball.domain.batting;

import lombok.Getter;

public enum BattingResultType {
    STRIKE("스트라이크"), BALL("볼"), NOTHING("낫싱");

    @Getter
    private final String type;

    BattingResultType(String type) {
        this.type = type;
    }
}
