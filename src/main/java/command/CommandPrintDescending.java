package command;

import error.InvalidArgument;
import managers.CollectionManager;
import classes.Organization;
import tools.InteractConsole;

import java.util.*;

public class CommandPrintDescending extends Command {
    public CommandPrintDescending(CollectionManager collection) {
        super("print_descending", "вывод элементов коллекции в порядке убывания", collection);
    }

    public void execute(String args) throws InvalidArgument {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        InteractConsole.printMessage("Элементы коллекции в порядке убывания:");
        ArrayList<Organization> sortedList = new ArrayList<>(this.getCollection().getOrganizations());
        Collections.sort(sortedList, Collections.reverseOrder(Organization.COMPARE_BY_SIZE));
        for (Organization org: sortedList) {
            InteractConsole.printSuccess(org.toString() + " " + org.getOrganizationSize());
        }
    }
    // https://stackoverflow.com/questions/3705275/help-comparing-float-member-variables-using-comparators
}
