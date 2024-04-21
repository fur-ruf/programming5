package files;

import classes.Organization;
import com.thoughtworks.xstream.security.AnyTypePermission;
import error.FileNotExist;
import error.RepeatId;
import error.XMLError;
import input.FileReader;
import managers.CollectionManager;
import managers.FileManager;
import tools.InteractConsole;
import tools.TakingConsole;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class CollectionReader {
    private CollectionManager collectionManager;
    private Scanner scanner;
    private InteractConsole console = new TakingConsole();
    public CollectionReader(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void getCollection(String path) throws FileNotExist, RepeatId, XMLError {
        FileReader fileReader = new FileReader();
        FileManager fileManager = new FileManager();

        XStream xstream = new XStream();
        xstream.alias("Organization", Organization.class);
        xstream.alias("Set", CollectionManager.class);
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.addImplicitCollection(CollectionManager.class, "organizations");
            HashSet collectionManagerWithObjects = (HashSet) xstream.fromXML(fileReader.readFromFile(fileManager.createFile(path)));
            Iterator<Organization> iterator = collectionManagerWithObjects.iterator();
            while (iterator.hasNext()) {
                Organization org = iterator.next();
                this.collectionManager.addID(org.getId());
                this.collectionManager.addOrganization(org);
            }
            Organization.updateId(collectionManager.getBiggestID());
    }
}

