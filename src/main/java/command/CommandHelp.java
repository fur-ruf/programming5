package command;

import error.InvalidArgument;
import managers.CommandManager;
import tools.Color;
import tools.InteractConsole;

/**
 * Реализовывает команду вывода информации о командах, доступных пользователю
 * @author Polik
 */
public class CommandHelp extends Command {
    private CommandManager commandManager;
    public CommandHelp(CommandManager commandManager) {
        super("help", "справка по доступным командам");
        this.commandManager = commandManager;
    }
    @Override
    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        InteractConsole.printMessage("Доступные команды:");
        for (Command command: commandManager.getCommands().values())
            InteractConsole.printSuccess(command.getName() + " : " + command.getDescription());
    }
}
