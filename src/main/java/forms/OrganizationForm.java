package forms;

import classes.Address;
import classes.Coordinates;
import classes.Organization;
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

public class OrganizationForm {
    private InteractConsole console;
    private Input scanner;

    public OrganizationForm() {
        if (FileManager.isInFile()) {
            scanner = new FileReader();
            console = new SilentConsole();
        } else {
            scanner = new UserReader();
            console = new TakingConsole();
        }
    }

    public Organization build() throws ErrorInFile, CantHaveScanner {
        return new Organization(askName(), askCoordinates(), askAnnualTurnover(), askEmployeesCount(), askType(), askOfficialAddress());
    }

    private String askName() throws ErrorInFile, CantHaveScanner {
        String input;
        while (true) {
            console.println("Введите имя", Color.PURPLE);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                console.printError("Имя не может быть пустым");
                if (FileManager.isInFile()) {
                    throw new ErrorInFile();
                }
            } else {
                return input;
            }
        }
    }

    private Coordinates askCoordinates() throws ErrorInFile, CantHaveScanner {
        return new CoordinatesForm().build();
    }

    private Float askAnnualTurnover() throws ErrorInFile, CantHaveScanner {
        String input;
        while(true) {
            console.println("Введите годовой оборот организации (типа float)", Color.PURPLE);
            input = scanner.nextLine().trim();
            try {
                float annualTurnover = Float.parseFloat(input);
                if (annualTurnover <= 0) throw new NumberFormatException();
                return annualTurnover;
            } catch (NumberFormatException e) {
                console.printError("Введенная строка некорректна. Необходимо число вида '12.3' или '12.3f', большее 0");
                if (FileManager.isInFile()) {
                    throw new ErrorInFile();
                }
            }
        }
    }

    private Long askEmployeesCount() throws ErrorInFile, CantHaveScanner {
        String input;
        while(true) {
            console.println("Введите количество работников организации (типа long)", Color.PURPLE);
            input = scanner.nextLine().trim();
            try {
                long employeesCount = Long.parseLong(input);
                if (employeesCount <= 0) throw new NumberFormatException();
                return employeesCount;
            } catch (NumberFormatException e) {
                console.printError("Введенная строка некорректна. Необходимо число больше 0");
                if (FileManager.isInFile()) {
                    throw new ErrorInFile();
                }
            }
        }
    }

    private OrganizationType askType() throws ErrorInFile, CantHaveScanner {
        return new OrganizationTypeForm().build();
    }

    private Address askOfficialAddress() throws ErrorInFile, CantHaveScanner {
        return new AddressForm().build();
    }
}
