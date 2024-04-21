package command;

import error.*;
import managers.CommandManager;
import managers.FileManager;
import input.FileReader;
import input.Input;
import input.UserReader;
import managers.CollectionManager;
import tools.InteractConsole;
import tools.SilentConsole;
import tools.TakingConsole;

import java.io.FileNotFoundException;

public abstract class Command {
    private String name;
    private String description;
    private CollectionManager collectionManager;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Command(String name, String description, CollectionManager collectionManager) {
        this.name = name;
        this.description = description;
        this.collectionManager = collectionManager;
//        if (FileManager.isInFile()) {
//            scanner = new FileReader();
//            console = new SilentConsole();
//        } else {
//            scanner = new UserReader();
//            console = new TakingConsole();
//        }
    }

    public abstract void execute(String args) throws InvalidArgument, IllegalRecursion, ErrorInFile, FileNotExist, CommandNotExist, CantHaveScanner;

    public CollectionManager getCollection() {
        return collectionManager;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}
