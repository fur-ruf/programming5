package command;

import java.util.Scanner;

import error.InvalidArgument;
import managers.ScannerManager;
import tools.InteractConsole;

public class CommandExit extends Command {
    public CommandExit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }
    @Override
    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        ScannerManager.getScanner().close();
        InteractConsole.printGoodbye("Спасибо за работу! Как говорится: от винта!");
        System.exit(0);
    }
}
