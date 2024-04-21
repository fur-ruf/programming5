package managers;

import classes.Organization;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import error.RepeatId;

import java.util.*;

public class CollectionManager {
    private String name = "Безымянная";
    private String type = "organization";
    private HashSet<Organization> organizations;
    private HashSet<Integer> ids = new HashSet<Integer>();
    private Date creationDate;
    private Date lastChange;
    private Date lastSave;

    public CollectionManager(){
        organizations = new HashSet<Organization>();
        creationDate = new Date();
        lastSave = new Date();
        lastChange = new Date();
    }
    public CollectionManager(String name){
        organizations = new HashSet<Organization>();
        creationDate = new Date();
        lastSave = new Date();
        lastChange = new Date();
        this.name = name;
    }
    public void addOrganization(Organization organization) {
        lastChange = new Date();
        organizations.add(organization);
    }

    public HashSet<Organization> getOrganizations() {
        return organizations;
    }

    public void clear() {
        lastChange = new Date();
        organizations.clear();
    }

    public boolean isEmpty() {
        return organizations.isEmpty();
    }
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        String output = "Информация о коллекции: имя - " + name + ", тип хранимых данных - " + type + ", размер коллекции - " +
                organizations.size() + ", время создания - " + creationDate + ", время последнего изменения - " + lastChange +
                ", время последнего сохранения - " + lastSave;
        return output;
    }

    public void addID(Integer id) throws RepeatId{
        boolean isAdd = ids.add(id);
        if (!isAdd) {
            throw new RepeatId("Повтор ID!");
        }
    }

    public void popID(Integer id) {
        ids.remove(id);
    }

    public int getBiggestID() {
        int value = 1;
        Iterator<Integer> iterator = ids.iterator();
        int max = -1;
        while (iterator.hasNext()) {
            value = iterator.next();
            if (max < value) {
                max = value;
            }
        }
        return value;
    }

    public void changeLastSave() {
        lastSave = new Date();
    }
}
