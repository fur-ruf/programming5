package command;

import error.FileNotExist;
import error.IllegalRecursion;
import error.InvalidArgument;
import files.CollectionWritter;
import managers.CollectionManager;
import managers.FileManager;

import java.io.File;

public class CommandSave extends Command {
    public CommandSave(CollectionManager collection) {
        super("save", "сохранить коллекцию в файл", collection);
    }

    public void execute(String args) throws FileNotExist, InvalidArgument {
        if (args.isEmpty()) {
            throw new InvalidArgument("Введены неверные аргументы");
        }
        CollectionWritter writter = new CollectionWritter(getCollection());
        writter.saveObjects(FileManager.createFile(args));
    }
}
