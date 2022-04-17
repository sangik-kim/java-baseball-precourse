package baseball.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public enum MenuOption {
    START(1), EXIT(2);

    private static final Map<Integer, MenuOption> BY_VALUE = new HashMap<>();

    static {
        for (MenuOption o : values()) {
            BY_VALUE.put(o.value, o);
        }
    }

    @Getter
    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public static MenuOption findByValue(int value) {
        return BY_VALUE.get(value);
    }
}
