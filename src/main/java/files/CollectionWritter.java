package files;

import com.thoughtworks.xstream.XStream;
import error.FileNotExist;
import managers.CollectionManager;
import tools.InteractConsole;
import tools.TakingConsole;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CollectionWritter {
    private final XStream xStream = new XStream();
    CollectionManager collectionManager;
    public CollectionWritter(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public void saveObjects(File file) throws FileNotExist {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(this.xStream.toXML(collectionManager.getOrganizations()));
            writer.close();
            collectionManager.changeLastSave();
        } catch (IOException e) {
            throw new FileNotExist("Файл не найден");
        }
    }
}
