package command;

import classes.*;
import error.CantHaveScanner;
import error.ErrorInFile;
import error.InvalidArgument;
import forms.OrganizationForm;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

import java.util.*;

public class CommandAddIfMin extends Command {
    public CommandAddIfMin(CollectionManager collection) {
        super("add_if_min", "добавление нового элемента в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", collection);
    }

    public void execute(String args) throws ErrorInFile, InvalidArgument, CantHaveScanner {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        Organization organization = new OrganizationForm().build();
        if (this.getCollection().isEmpty()) {
            this.getCollection().addOrganization(organization);
            InteractConsole.printSuccess("Организация добавлена!");
        } else {
            Iterator<Organization> iterator = this.getCollection().getOrganizations().iterator();
            float min = iterator.next().getOrganizationSize();
            while (iterator.hasNext()) {
                Organization org = iterator.next();
                if (min > org.getOrganizationSize()) {
                    min = org.getOrganizationSize();
                }
            }
            if (min > organization.getOrganizationSize()) {
                this.getCollection().addOrganization(organization);
                InteractConsole.printSuccess("Организация добавлена!");
            }
        }
        InteractConsole.printMessage("Операция успешно выполнена!");
    }
}
