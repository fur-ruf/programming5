package command;

import error.InvalidArgument;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

public class CommandClear extends Command{
    public CommandClear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", collectionManager);
    }

    @Override
    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        this.getCollection().clear();
        InteractConsole.printMessage("Операция выполнена!");
    }
}
