package baseball.domain;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public enum BallIndex {
    FIRST(0), SECOND(1), THIRD(2);

    private static final Map<Integer, BallIndex> BY_VALUE = new HashMap<>();

    static {
        for (BallIndex b : values()) {
            BY_VALUE.put(b.value, b);
        }
    }

    @Getter
    private final int value;

    BallIndex(int value) {
        this.value = value;
    }

    public static BallIndex findByValue(int value) {
        return BY_VALUE.get(value);
    }
}
