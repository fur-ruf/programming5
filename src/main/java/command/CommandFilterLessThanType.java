package command;

import error.InvalidArgument;
import managers.CollectionManager;
import classes.Organization;
import classes.OrganizationType;
import tools.InteractConsole;

import java.util.Iterator;

public class CommandFilterLessThanType extends Command {
    public CommandFilterLessThanType(CollectionManager collection) {
        super("filter_less_than_type type", "вывод элемента, значение поля type которого меньше заданного", collection);
    }

    @Override
    public void execute(String args) throws InvalidArgument {
        String arg = args.toUpperCase();
        try {
            int max = OrganizationType.valueOf(arg).getTax();
            Iterator<Organization> iterator = this.getCollection().getOrganizations().iterator();
            while (iterator.hasNext()) {
                Organization org = iterator.next();
                if (org.getOrganizationType().getTax() < max) {
                    InteractConsole.printSuccess(org.toString());
                }
            }
            InteractConsole.printMessage("Работа команды завершена");
        } catch (IllegalArgumentException e) {
            throw new InvalidArgument("Введенный type не найден. Работа команды прервана");
        }
    }
}
