package input;

import managers.ScannerManager;

import java.util.Scanner;

public class UserReader extends Input{
    private static Scanner scanner = ScannerManager.getScanner();

    public String nextLine() {
        return scanner.nextLine();
    }
}
