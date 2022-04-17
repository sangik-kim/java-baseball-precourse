package baseball.controller;

import baseball.domain.Baseball;
import baseball.domain.BaseballGame;
import baseball.domain.BaseballGameConfiguration;
import baseball.dto.GameResult;
import baseball.dto.MenuInput;
import baseball.dto.MenuOption;
import baseball.dto.UserBallNumbers;
import baseball.view.GameUserInterface;

public class BaseballGameController {

    private final GameUserInterface userInterface;

    public BaseballGameController(GameUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    /**
     * start the baseball game
     */
    public void start() {
        BaseballGameConfiguration configuration = BaseballGameConfiguration.defaultConfiguration();
        BaseballGame game = new BaseballGame(Baseball.create(configuration));
        game.start();
        play(game);
    }

    private void play(BaseballGame game) {
        UserBallNumbers userBallNumbers = getUserPrediction();
        GameResult result = game.play(userBallNumbers);
        userInterface.show(result.getMessage());
        if (Boolean.TRUE.equals(result.getIsWon())) {
            success();
            return;
        }
        play(game);
    }

    private UserBallNumbers getUserPrediction() {
        String userInput = userInterface.getUserInput("숫자를 입력해주세요: ");
        return UserBallNumbers.from(userInput);
    }

    private void success() {
        userInterface.show("3개의 숫자를 모두 맞히셨습니다! 게임 종료\n");
        menu();
    }

    private void menu() {
        String userInput = userInterface.getUserInput("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.\n");
        MenuInput menuInput = MenuInput.from(userInput);
        if (menuInput.getInput() == MenuOption.START) {
            start();
        }
    }
}
