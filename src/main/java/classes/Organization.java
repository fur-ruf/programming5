package classes;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDate;
import java.util.Comparator;

@XStreamAlias("Organization")
public class Organization {
    private static Integer all_id = 0;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float annualTurnover; //Значение поля должно быть больше 0
    private Long employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null

    public Organization(String name, Coordinates coordinates, float annualTurnover, long employeesCount, OrganizationType type, Address officialAddress) {
        all_id++;
        id = all_id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDate.now();
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    @Override
    public String toString() {
        String output = String.format(
                "`Organization` ID: %s; Name: %s; Coordinates: (%s, %s); CreationDate: %s, EmployeesCount: %s, AnnualTurnover: %s, OrganizationType: %s, Address: %s, %s",
                this.getId(),
                this.getName(),
                this.getCoordinates().getX(), this.getCoordinates().getY(),
                this.getCreationDate().toString(),
                this.getEmployeesCount(),
                this.getAnnualTurnover(),
                this.getOrganizationType(),
                this.getOfficialAddress().getStreet(),
                this.getOfficialAddress().getZipCode());
        return output;
    }
    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public OrganizationType getOrganizationType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public float getAnnualTurnover() {
        return annualTurnover;
    }

    public void setId(int id) {
        this.id = id;
    }
    public float getOrganizationSize() {
        return (float) (100 - type.getTax()) / 100 * annualTurnover / employeesCount ;
    }
    public static final Comparator<Organization> COMPARE_BY_SIZE = new Comparator<Organization>() {
        @Override
        public int compare(Organization lhs, Organization rhs) {
            if (lhs.getOrganizationSize() < rhs.getOrganizationSize()) return -1;
            if (lhs.getOrganizationSize() == rhs.getOrganizationSize()) return 0;
            return 1;
        }
    };

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    public static void updateId(Integer id) {
        all_id = id;
    }
}
