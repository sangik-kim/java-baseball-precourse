package baseball.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

public class MenuInput {

    private static final Pattern MENU_OPTION_PATTERN = Pattern.compile("^[1|2]$");

    @Getter
    private final MenuOption input;

    private MenuInput(MenuOption input) {
        this.input = input;
    }

    public static MenuInput from(String input) {
        validate(input);
        return new MenuInput(MenuOption.findByValue(Integer.valueOf(input)));
    }

    private static void validate(String input) {
        Matcher matcher = MENU_OPTION_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("[%s]는 입력할 수 없는 옵션입니다. 애플리케이션 종료!", input));
        }
    }
}
