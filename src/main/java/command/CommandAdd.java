package command;

import classes.*;
import error.CantHaveScanner;
import error.ErrorInFile;
import error.InvalidArgument;
import forms.OrganizationForm;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

public class CommandAdd extends Command{
    public CommandAdd(CollectionManager collection){
        super("add", "добавление нового элемента в коллекцию. При введении 'stop' выполнение будет прервано", collection);
    }

    @Override
    public void execute(String args) throws ErrorInFile, InvalidArgument, CantHaveScanner {
        if (!args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        Organization organization = new OrganizationForm().build();
        this.getCollection().getOrganizations().add(organization);
        InteractConsole.printMessage("Организация успешно добавлена!");
    }
}
