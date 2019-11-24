package cloud.ptl.menu;

import cloud.ptl.persistence.Address;
import cloud.ptl.persistence.Contact;
import cloud.ptl.persistence.ContactManager;

/**
 * Used to adding new contacts to ContactBook. Each contact entry should have rigid set of data, which can be found in
 * Contact class
 * @see Contact
 */
public class AddContactMenu extends Menu {
    private String name;
    private String surname;
    private String phoneNumber;
    private String streetName;
    private Integer homeNumber;
    private Integer flatNumber;
    private String postOffice;
    private String postalCode;

    public AddContactMenu(){
        // setting value lower than -1 is necessary to determine if user entered any input, because
        // if user leave empty field for homeNumber or flatNumber it is being assigned -1,
        // and -1 means input (empty) was given
        homeNumber = -2;
    }

    @Override
    /**
     * Collects information about new contact, it is possible to leave empty values
     * then string becomes empty string ("") and int values become -1
     */
    public void show() {
        System.out.println("DODAWANIE KONTAKTU");
        System.out.println("menu glowne --> dodawanie kontaktu");

        System.out.println("Podaj imie: ");
        name = Menu.readResponseString();

        System.out.println("Podaj nazwiko: ");
        surname = Menu.readResponseString();

        System.out.println("Podaj numer telefonu: ");
        phoneNumber = Menu.readResponseString();

        System.out.println("Podaj nazwe ulicy: ");
        streetName = Menu.readResponseString();

        System.out.println("Podaj numer domu: ");
        homeNumber = Menu.readResponseInteger(true);

        System.out.println("Podaj numer mieszkania: ");
        flatNumber = Menu.readResponseInteger(true);

        System.out.println("Podaj nazwe placowki pocztowej: ");
        postOffice = Menu.readResponseString();

        System.out.println("Podaj kod pocztowy: ");
        postalCode = Menu.readResponseString();

        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");

    }

    @Override
    /**
     * Adding new contact to contact book. ContactManager saves, contact book with new value, by itself
     */
    public void execute() {
        Address newAddress = new Address(streetName, homeNumber, flatNumber, postalCode, postOffice);
        Contact newContact = new Contact(name, surname, phoneNumber, newAddress);
        ContactManager.addContact(newContact);
        System.out.println("Doadano kontakt: ");
        System.out.println(newContact);
        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    /**
     * Interaction with user is ended if user goes through entire get-value set-value process, which is remarked as change in homeNumber, or
     * flatNumber. It is impossible to track changes in other values, because freshly initialised string and empty string looks this same
     */
    public Boolean shouldBreak() {
        return homeNumber != -2;
    }
}
