package baseball.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

public class UserBallNumbers {

    private static final Pattern BALL_NUMBER_PATTERN = Pattern.compile("^[1-9]+$");

    @Getter
    private final List<Integer> ballNumbers;

    private UserBallNumbers(List<Integer> ballNumbers) {
        this.ballNumbers = ballNumbers;
    }

    public static UserBallNumbers from(String input) {
        validate(input);

        List<Integer> ballNumbers = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            ballNumbers.add(Character.getNumericValue(input.charAt(i)));
        }
        return new UserBallNumbers(ballNumbers);
    }

    private static void validate(String input) {
        Matcher matcher = BALL_NUMBER_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("[%s]는 올바른 숫자가 아닙니다. 애플리케이션 종료!", input));
        }
    }
}
