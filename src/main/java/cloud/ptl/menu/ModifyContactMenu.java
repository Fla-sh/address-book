package cloud.ptl.menu;

import cloud.ptl.persistence.Contact;
import cloud.ptl.persistence.ContactManager;
import org.apache.commons.lang.SerializationUtils;

public class ModifyContactMenu extends Menu {
    private String hash;

    public ModifyContactMenu(){
        hash = "";
    }

    @Override
    public void show() {
        System.out.println("MODYFIKOWANIE KONTAKTU");
        System.out.println("menu glowne --> modyfikowanie kontaktu");
        System.out.println("Podaj hash kontaktu");
        hash = Menu.readResponseString();
        System.out.print("\n\n");
    }

    @Override
    public void execute() {
        Integer contactID = ContactManager.getContactIDFromHash(hash);
        if(contactID != -1) {
            Contact oldContact = (Contact) SerializationUtils.clone(ContactManager.getContact(contactID));
            String newName;
            String newSurname;
            String newPhoneNumber;
            String newStreetName;
            Integer newHomeNumber;
            Integer newFlatNumber;
            String newPostOffice;
            String newPostalCode;

            System.out.println("Aby zachowac poprzednia wartosc pozostaw pole puste");
            System.out.println("Imie [" + oldContact.getName() + "]: ");
            newName = Menu.readResponseString();

            System.out.println("Nazwisko [" + oldContact.getSurname() + "]");
            newSurname = Menu.readResponseString();

            System.out.println("Telefon [" + oldContact.getPhoneNumber() + "]: ");
            newPhoneNumber = Menu.readResponseString();

            System.out.println("Ulica [" + oldContact.getAddress().getStreetName() + "]:");
            newStreetName = Menu.readResponseString();

            System.out.println("Numer domu [" + oldContact.getAddress().getHomeNumber() + "]:");
            newHomeNumber = Menu.readResponseInteger(true);

            System.out.println("Numer mieszkania [" + oldContact.getAddress().getRoomNumber() + "]: ");
            newFlatNumber = Menu.readResponseInteger(true);

            System.out.println("Kod pocztowy [" + oldContact.getAddress().getPostalCode() + "]:");
            newPostalCode = Menu.readResponseString();

            System.out.println("Poczta [" + oldContact.getAddress().getPostOffice() + "]:");
            newPostOffice = Menu.readResponseString();

            System.out.println("Zmiany: ");

            if(!newName.equals("")){
                ContactManager.getContact(contactID).setName(newName);
                System.out.println(String.format("Imie:\t\t\t\t %s/%s",
                        oldContact.getName(), ContactManager.getContact(contactID).getName()));
            }
            if(!newSurname.equals("")){
                ContactManager.getContact(contactID).setSurname(newSurname);
                System.out.println(String.format("Nazwisko:\t\t\t %s/%s",
                        oldContact.getSurname(), ContactManager.getContact(contactID).getSurname()));
            }
            if(!newPhoneNumber.equals("")){
                ContactManager.getContact(contactID).setPhoneNumber(newPhoneNumber);
                System.out.println(String.format("Telefon:\t\t\t %s/%s",
                        oldContact.getPhoneNumber(), ContactManager.getContact(contactID).getPhoneNumber()));
            }
            if(!newStreetName.equals("")){
                ContactManager.getContact(contactID).getAddress().setStreetName(newStreetName);
                System.out.println(String.format("Ulica:\t\t\t\t %s/%s",
                        oldContact.getAddress().getStreetName(), ContactManager.getContact(contactID).getAddress().getStreetName()));
            }
            if(newHomeNumber != -1){
                ContactManager.getContact(contactID).getAddress().setHomeNumber(newHomeNumber);
                System.out.println(String.format("Dom:\t\t\t\t %s/%s",
                        oldContact.getAddress().getHomeNumber().toString(), ContactManager.getContact(contactID).getAddress().getHomeNumber().toString()));
            }
            if(newFlatNumber != -1 ){
                ContactManager.getContact(contactID).getAddress().setRoomNumber(newFlatNumber);
                System.out.println(String.format("Mieszkanie:\t\t\t %s/%s",
                        oldContact.getAddress().getRoomNumber(), ContactManager.getContact(contactID).getAddress().getRoomNumber()));
            }
            if(!newPostalCode.equals("")){
                ContactManager.getContact(contactID).getAddress().setPostalCode(newPostalCode);
                System.out.println(String.format("Kod pocztowy:\t\t %s/%s",
                        oldContact.getAddress().getPostalCode(), ContactManager.getContact(contactID).getAddress().getPostalCode()));
            }
            if(!newPostOffice.equals("")){
                ContactManager.getContact(contactID).getAddress().setPostOffice(newPostOffice);
                System.out.println(String.format("Poczta:\t\t\t\t %s/%s",
                        oldContact.getAddress().getPostOffice(), ContactManager.getContact(contactID).getAddress().getPostOffice()));
            }
            ContactManager.getContact(contactID).recreateHash();

            System.out.print("\n\n");
            System.out.println("Nowa wartosc kontaktu: ");
            System.out.print(ContactManager.getContact(contactID));
            System.out.println("\n\n");
            System.out.println("------------------------------");
            System.out.println("\n\n");
        }
        else{
            System.out.println("Nie znaleziono kontaktu o takmi hashu");
        }
    }

    @Override
    public Boolean shouldBreak() {
        return !hash.equals("");
    }
}
