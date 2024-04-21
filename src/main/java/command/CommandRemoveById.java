package command;

import classes.*;
import error.InvalidArgument;
import managers.CollectionManager;
import tools.Color;
import tools.InteractConsole;

import java.util.Iterator;

public class CommandRemoveById extends Command{
    public CommandRemoveById(CollectionManager collection) {
        super("remove_by_id id", "удаление элемента из коллекции по его id", collection);
    }

    @Override
    public void execute(String args) throws InvalidArgument{
        int arg = 0;
        try {
            arg = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidArgument("Ошибка в ходе выполнения команды " + getName() + " неверный id");
        }
        Iterator<Organization> iterator = this.getCollection().getOrganizations().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == arg) {
                this.getCollection().popID(arg);
                iterator.remove();
                InteractConsole.printMessage("Организация успешно удалена!");
                return;
            }
        }
        InteractConsole.printMessage("Организация не удалена! Введенное ID не найдено");
    }
}
