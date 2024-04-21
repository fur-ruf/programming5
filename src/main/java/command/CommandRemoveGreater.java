package command;

import classes.*;
import error.CantHaveScanner;
import error.ErrorInFile;
import error.InvalidArgument;
import forms.OrganizationForm;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

import java.util.Iterator;

public class CommandRemoveGreater extends Command {
    public CommandRemoveGreater(CollectionManager collection) {
        super("remove_greater", "удаление из коллекции всех элементов, превышающих заданный", collection);
    }

    @Override
    public void execute(String args) throws ErrorInFile, InvalidArgument, CantHaveScanner {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        Organization organization = new OrganizationForm().build();
        Iterator<Organization> iterator = this.getCollection().getOrganizations().iterator();
        while (iterator.hasNext()) {
            Organization org = iterator.next();
            if (org.getOrganizationSize() > organization.getOrganizationSize()) {
                this.getCollection().popID(org.getId());
                iterator.remove();
                InteractConsole.printSuccess("Организация " + org +  " удалена");
            }
        }
        InteractConsole.printMessage("Операция успешно выполнена!");
    }
}
