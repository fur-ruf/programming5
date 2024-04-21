package command;

import classes.*;
import error.InvalidArgument;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

public class CommandShow extends Command{
    public CommandShow(CollectionManager collectionManager) {
        super("show", "вывод всех элементов коллекции в строковом представлении", collectionManager);
    }
    @Override
    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        InteractConsole.printMessage("Элементы коллекции:");
        for (Organization org: this.getCollection().getOrganizations()) {
            InteractConsole.printSuccess(org.toString());
        }
    }
}
