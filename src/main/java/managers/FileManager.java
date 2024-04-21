package managers;

import error.CantHaveScanner;
import error.FileNotExist;
import error.IllegalRecursion;
import tools.InteractConsole;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class FileManager {
    private static Stack<File> files = new Stack<>();
    private static Stack<Scanner> scanners = new Stack<>();

    public static boolean isInFile() {
        return !files.empty();
    }

    public static File getFileOnTop() {
        return files.pop();
    }

    public Scanner getScannerOnTop() {
        return scanners.peek();
    }

    public static File createFile(String path) throws FileNotExist {
        File file = new File(path);
        if (file == null) {
            throw new FileNotExist("Указанный файл" + path + "не существует");
        }
        return file;
    }

    public void createFileInExecute(String path) throws FileNotExist, IllegalRecursion {
        File file = createFile(path);
        haveRecursion(file);
        try {
            Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(file)));
            scanners.add(scanner);
            files.add(file);
        } catch (FileNotFoundException e) {
            throw new FileNotExist("Указанный файл " + path + " не существует");
        }
    }

    public void haveRecursion (File file) throws IllegalRecursion {
        if (files.search(file) != -1) {
            files.clear();
            for (Scanner scanner: scanners) {
                scanner.close();
            }
            scanners.clear();
            throw new IllegalRecursion("Замечена рекурсия!");
        }
    }

    public Scanner getScanner() throws CantHaveScanner {
        boolean notHaveLine = true;
        if (scanners.size() == 0) {
            throw new CantHaveScanner();
        }
        while (notHaveLine) {
            if (getScannerOnTop().hasNext()) {
                return getScannerOnTop();
            } else {
                scanners.pop();
                files.pop();
                if (scanners.size() == 0) {
                    throw new CantHaveScanner();
                }
            }
        }
        throw new CantHaveScanner();
    }
}
