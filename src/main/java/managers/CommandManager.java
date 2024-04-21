package managers;

import command.Command;
import error.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class CommandManager {
    private HashMap<String, Command> commands = new HashMap<>();
    private ArrayDeque<String> commandHistory = new ArrayDeque<>();

    public void addCommand(String help, Command command) {
        commands.put(help, command);
    }

    public void addToHistory(String command) {
        if (commandHistory.size() == 7) {
            commandHistory.pollFirst();
        }
        commandHistory.add(command);
    }

    public ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void execute(String name, String args) throws CommandNotExist, InvalidArgument, IllegalRecursion, ErrorInFile, FileNotExist, CantHaveScanner {
        Command command = commands.get(name);
        if (command == null) throw new CommandNotExist("Введенная команда не существует");
        command.execute(args.trim());
        addToHistory(name + " " + args);
    }
}
