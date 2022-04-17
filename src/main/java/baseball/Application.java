package baseball;

import baseball.controller.BaseballGameController;
import baseball.view.ConsoleUserInterface;
import baseball.view.GameUserInterface;
import baseball.view.printer.ConsolePrinter;
import baseball.view.scanner.ConsoleScanner;

public class Application {
    public static void main(String[] args) {
        GameUserInterface gameUserInterface = new ConsoleUserInterface(new ConsolePrinter(), new ConsoleScanner());
        BaseballGameController controller = new BaseballGameController(gameUserInterface);
        controller.start();
    }
}
