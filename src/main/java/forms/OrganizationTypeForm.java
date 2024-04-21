package forms;

import classes.OrganizationType;
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

public class OrganizationTypeForm {
    private InteractConsole console;
    private Input scanner;

    public OrganizationTypeForm() {
        if (FileManager.isInFile()) {
            scanner = new FileReader();
            console = new SilentConsole();
        } else {
            scanner = new UserReader();
            console = new TakingConsole();
        }
    }

    public OrganizationType build() throws ErrorInFile, CantHaveScanner {
        this.console.print("Доступные типы компаний: ", Color.PURPLE);
        this.console.println(OrganizationType.getValues(), Color.PURPLE);

        while (true) {
            this.console.println("Выберите тип компании: ", Color.PURPLE);
            String input = this.scanner.nextLine().trim();

            try {
                return OrganizationType.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                this.console.printError("Тип компании не найден");
                if (FileManager.isInFile()) {
                    throw new ErrorInFile();
                }
            }
        }
    }
}
