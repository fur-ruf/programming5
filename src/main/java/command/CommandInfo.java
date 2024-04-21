package command;

import error.InvalidArgument;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

public class CommandInfo extends Command {
    public CommandInfo(CollectionManager collection) {
        super("info", "вывод информации о коллекции", collection);
    }
    @Override
    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        InteractConsole.printMessage("Информация о коллекции:");
        InteractConsole.printSuccess(this.getCollection().toString());
    }
}
