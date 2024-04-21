package command;

import classes.*;
import error.InvalidArgument;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

import java.util.Iterator;

public class CommandMaxById extends Command {
    public CommandMaxById(CollectionManager collectionManager) {
        super("max_by_id", "вывод любого объекта из коллекции, значение поля id которого является максимальным", collectionManager);
    }

    @Override
    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        if (this.getCollection().isEmpty()) {
            InteractConsole.printMessage("В коллекции нет элементов");
        } else {
            Iterator<Organization> iterator = this.getCollection().getOrganizations().iterator();
            int max = -1;
            Organization organization = null;
            while (iterator.hasNext()) {
                Organization org = iterator.next();
                if (org.getId() > max) {
                    max = org.getId();
                    organization = org;
                }
            }
            InteractConsole.printMessage("Организация с наибодьшим id:");
            InteractConsole.printSuccess(organization.toString());
        }
    }
}
