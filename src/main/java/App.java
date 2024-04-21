import command.*;
import error.FileNotExist;
import error.IllegalRecursion;
import error.InvalidArgument;
import error.RepeatId;
import files.CollectionReader;
import managers.*;
import tools.Color;
import tools.InteractConsole;
import tools.TakingConsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        CollectionManager collection = new CollectionManager("Замурчательная лабушка");
        CommandManager commandManager = new CommandManager();

        {
            commandManager.addCommand("help", new CommandHelp(commandManager));
            commandManager.addCommand("info", new CommandInfo(collection));
            commandManager.addCommand("show", new CommandShow(collection));
            commandManager.addCommand("add", new CommandAdd(collection));
            commandManager.addCommand("update", new CommandUpdate(collection));
            commandManager.addCommand("remove_by_id", new CommandRemoveById(collection));
            commandManager.addCommand("clear", new CommandClear(collection));
            commandManager.addCommand("save", new CommandSave(collection));
            commandManager.addCommand("execute_script", new CommandExecuteScript());
            commandManager.addCommand("exit", new CommandExit());
            commandManager.addCommand("add_if_min", new CommandAddIfMin(collection));
            commandManager.addCommand("remove_greater", new CommandRemoveGreater(collection));
            commandManager.addCommand("history", new CommandHistory(commandManager));
            commandManager.addCommand("max_by_id", new CommandMaxById(collection));
            commandManager.addCommand("filter_less_than_type", new CommandFilterLessThanType(collection));
            commandManager.addCommand("print_descending", new CommandPrintDescending(collection));
        }

        new WorkManager(commandManager, collection).work();
    }
}