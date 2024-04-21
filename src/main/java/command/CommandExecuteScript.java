package command;

import error.*;
import input.FileReader;
import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;
import managers.WorkManager;
import tools.Color;
import tools.InteractConsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandExecuteScript extends Command {
    private final FileReader fileReader = new FileReader();
    private FileManager fileManager = new FileManager();

    public CommandExecuteScript() {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла");
    }

    @Override
    public void execute(String args) throws IllegalRecursion, FileNotExist, CommandNotExist, ErrorInFile, InvalidArgument, CantHaveScanner {
        if (args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        fileManager.createFileInExecute(args);
        InteractConsole.printSuccess("Файл успешно найден!");
        while (FileManager.isInFile()) {
            String commandWithArg = fileReader.nextLine().trim() + " ";
            WorkManager.commandExecute(commandWithArg.split(" ", 2));
        }
        InteractConsole.printMessage("Операция успешно выполнена!");
    }
}
