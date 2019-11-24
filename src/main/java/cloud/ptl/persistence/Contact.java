package cloud.ptl.persistence;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;

/**
 * Represents one contact in Contact Book. Each contact is diversified by its own hash, which is created as combination of all field in Contact class.
 * Two contacts can have same hash if they contains same information. Contact Book then will have two entries with this same hash, however it is not a problem
 * because all methods will be executed on older one
 */
public class Contact implements Serializable{
    private String hash;
    private Address address;
    private String name;
    private String surname;
    private String phoneNumber;

    public Contact(String name, String surname, String phoneNumber, Address address){
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.surname = surname;
        recreateHash();
    }

    public Contact(){}

    /**
     * Recreates hash of contact, no hash collision included
     */
    public void recreateHash(){
        hash = DigestUtils.sha1Hex(name + surname + phoneNumber + address.toString());
    }

    @Override
    /**
     * Converts Contact Object into String
     */
    public String toString() {
        return String.format(   "Hash \t %s \n" +
                                "Imie: \t\t\t\t %s\n" +
                                "Nazwisko: \t\t\t %s\n" +
                                "Telefon: \t\t\t %s\n" +
                                "Ulica: \t\t\t\t %s\n" +
                                "Dom: \t\t\t\t %d\n" +
                                "Mieszkanie: \t\t %d\n" +
                                "Kod pocztowy: \t\t %s\n" +
                                "Poczta: \t\t\t %s",
                hash,
                name,
                surname,
                phoneNumber,
                address.getStreetName(),
                address.getHomeNumber(),
                address.getRoomNumber(),
                address.getPostalCode(),
                address.getPostOffice());
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public String getHash(){return hash;}

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
