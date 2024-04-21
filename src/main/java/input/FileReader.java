package input;

import error.CantHaveScanner;
import error.FileNotExist;
import managers.FileManager;
import managers.ScannerManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader extends Input{
    private static Scanner scanner;
    private FileManager fileManager = new FileManager();

    public String nextLine() throws CantHaveScanner {
        scanner = fileManager.getScanner();
        return scanner.nextLine();
    }

    // Используется в CollectionReader
    public String readFromFile(File file) throws FileNotExist {
        try {
            Scanner scannerForReading = new Scanner(file);
            StringBuilder stringBuilder = new StringBuilder();
            while (scannerForReading.hasNext()) {
                stringBuilder.append(scannerForReading.nextLine());
            }
            if (stringBuilder.isEmpty()) {
                return "<set></set>";
            }
            scannerForReading.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            throw new FileNotExist("Указанный файл не существует");
        }
    }
}
