package error;

import managers.FileManager;

import javax.management.RuntimeErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ErrorInFile extends IOException {
    public ErrorInFile() {
        super("В файле " + FileManager.getFileOnTop().getName() + " ошибка");
    }
}
