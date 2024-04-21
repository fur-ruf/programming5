package classes;

public class Address {
    private String street; //Поле может быть null
    private String zipCode; //Поле не может быть null, длина <= 18

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

//    if (street == null) {
//        throw new ErrorInEnteredValue("Значение в параметре 'street' равно пустой строке");
//    }
//        if (zipCode == null) {
//        throw new ErrorInEnteredValue("Значение в параметре 'zipCode' равно пустой строке");
//    }

    public boolean isValid() {
        if (street == null | zipCode == null) {
            return false;
        }
        return true;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
}
