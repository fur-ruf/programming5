package command;

import error.InvalidArgument;
import managers.CommandManager;
import tools.Color;
import tools.InteractConsole;

/**
 * Реализовывает команду вывода истории действий пользователя
 * @author Polik
 */
public class CommandHistory extends Command{
    private CommandManager commandManager;
   public CommandHistory(CommandManager commandManager) {
        super("history", "вывод последних 6 команд (без их аргументов)");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        InteractConsole.printMessage("История ваших действий: ");
        InteractConsole.printSuccess(String.join(" ", commandManager.getCommandHistory()));
    }
}
