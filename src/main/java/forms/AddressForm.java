package forms;

import classes.Address;
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

public class AddressForm {
    private InteractConsole console;
    private Input scanner;

    public AddressForm() {
        if (FileManager.isInFile()) {
            scanner = new FileReader();
            console = new SilentConsole();
        } else {
            scanner = new UserReader();
            console = new TakingConsole();
        }
    }

    public Address build() throws ErrorInFile, CantHaveScanner {
        this.console.println("Введите адрес организации в следующем формате: улица почтовый_индекс", Color.PURPLE);

        while (true) {
            String input = scanner.nextLine();

            String[] words = input.split(" ");
            if (words.length < 2) {
                console.printError("Введенная строка некорректна. Необходима непустая строка");
                if (FileManager.isInFile()) {
                    throw new ErrorInFile();
                }
            }
            else {
                if (words[words.length - 1].length() > 18) {
                    console.printError("Введенная строка некорректна. Индекс не должен быть больше 18 символов");
                    if (FileManager.isInFile()) {
                        throw new ErrorInFile();
                    }
                } else {
                    String[] wordStreet = new String[words.length - 1];
                    for (int i = 0; i < wordStreet.length; i++) {
                        wordStreet[i] = words[i];
                    }
                    return new Address(String.join(" ", wordStreet), words[words.length - 1]);
                }
            }
        }
    }
}

