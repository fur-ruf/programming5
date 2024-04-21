package error;

import java.io.FileNotFoundException;

public class FileNotExist extends FileNotFoundException {
    public FileNotExist(String message) {
        super(message);
    }
}
