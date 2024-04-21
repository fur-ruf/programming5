package forms;

import classes.Coordinates;
import error.CantHaveScanner;
import error.ErrorInFile;
import managers.FileManager;
import input.FileReader;
import input.Input;
import input.UserReader;
import tools.Color;
import tools.InteractConsole;
import tools.SilentConsole;
import tools.TakingConsole;

public class CoordinatesForm {
    private InteractConsole console;
    private Input scanner;

    public CoordinatesForm() {
        if (FileManager.isInFile()) {
            scanner = new FileReader();
            console = new SilentConsole();
        } else {
            scanner = new UserReader();
            console = new TakingConsole();
        }
    }

    public Coordinates build() throws ErrorInFile, CantHaveScanner {
        this.console.println("Введите координаты в следующем формате (типа double): х у", Color.PURPLE);

        while (true) {
            String input = scanner.nextLine();
            String[] values = input.split(" ");
            try {
                Double x = Double.parseDouble(values[0]);
                Double y = Double.parseDouble(values[1]);
                return new Coordinates(x, y);
            } catch (NumberFormatException e) {
                console.printError("Введенная строка некорректна. Необходимы два числа вида '12.3 13.8'");
                if (FileManager.isInFile()) {
                    throw new ErrorInFile();
                }
            }
        }
    }
}
