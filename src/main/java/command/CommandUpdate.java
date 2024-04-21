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

public class CommandUpdate extends Command{
    public CommandUpdate(CollectionManager collection) {
        super("update id", "обновление значениея элемента коллекции, id которого равен заданному", collection);
    }

    @Override
    public void execute(String args) throws InvalidArgument, ErrorInFile, CantHaveScanner {
        boolean idNotExist = true;
        int arg = 0;
        try {
            arg = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidArgument("Ошибка в ходе выполнения команды " + getName() + " неверный id");
        }
        Iterator<Organization> iterator = this.getCollection().getOrganizations().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == arg) {
                iterator.remove();
                idNotExist = false;
            }
        }
        if (idNotExist) {
            throw new InvalidArgument("Ошибка в ходе выполнения команды " + getName() + " неверный id");
        }
        Organization organization = new OrganizationForm().build();
        organization.setId(arg);
        this.getCollection().addOrganization(organization);
        InteractConsole.printMessage("Организация успешно обновлена!");
    }
}
