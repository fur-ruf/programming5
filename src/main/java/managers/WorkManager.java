package managers;

import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.StreamException;
import command.CommandExit;
import error.*;
import files.CollectionReader;
import input.FileReader;
import input.Input;
import input.UserReader;
import tools.Color;
import tools.InteractConsole;
import tools.SilentConsole;
import tools.TakingConsole;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WorkManager {
    private static CommandManager commandManager = null;
    private CollectionManager collectionManager;
    private Input scanner;
    private InteractConsole console = new TakingConsole();
    private boolean readFromFile = true;

    public WorkManager(CommandManager commandManager, CollectionManager collectionManager) {
        scanner = new UserReader();
        WorkManager.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    public void sayHiAndReadFromFile(CollectionManager collection) throws FileNotExist, RepeatId, XMLError, CantHaveScanner {
        while (readFromFile) {
            console.println("Введите путь до файла", Color.PURPLE);
            CollectionReader reader = new CollectionReader(collection);
            reader.getCollection(scanner.nextLine());
            this.readFromFile = false;
            console.println("Чтение выполнено!", Color.PURPLE);
        }
    }

    public void work() {
        console.println("Пиривет! Мчись вперёд навстречу приключениям и бага... кхм, кхм, это шутка, плохая шутка. Начнём!", Color.PURPLE);
        while (true) {
            try {
                sayHiAndReadFromFile(collectionManager);
                String commandWithArg = scanner.nextLine().trim() + " ";
                this.commandExecute(commandWithArg.split(" ", 2));
            } catch (InvalidArgument | FileNotExist | CommandNotExist e) {
                console.printError(e.getMessage());
            } catch (IllegalRecursion | ErrorInFile | XMLError | NoSuchElementException | RepeatId e) {
                console.printError(e.getMessage());
                console.printError("Такие ошибки непростительны!");
                console.printGoodbye("Спасибо за работу! Как говорится: от винта!");
                System.exit(0);
            } catch (XStreamException e) {
                console.printError("В файле обнаружены ошибки, проверьте данные!");
            } catch (CantHaveScanner e) {
                console.println("Чтение из файла прекращено", Color.WHITE);
            }
        }
    }

    public static void commandExecute(String[] commandWithArg) throws CommandNotExist, InvalidArgument, IllegalRecursion, FileNotExist, ErrorInFile, CantHaveScanner {
        if (commandWithArg[0].equals("")) {
            throw new CommandNotExist("Введенная команда не существует");
        }
        commandManager.execute(commandWithArg[0], commandWithArg[1]);
    }
}
