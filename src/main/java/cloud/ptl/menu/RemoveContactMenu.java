package cloud.ptl.menu;

import cloud.ptl.persistence.Contact;
import cloud.ptl.persistence.ContactManager;

/**
 * Class remove contact from Contact Book
 */
public class RemoveContactMenu extends Menu {
    private String hash;

    public RemoveContactMenu(){
        hash = "";
    }

    @Override
    /**
     * Asks user which contact to remove
     */
    public void show() {
        System.out.println("USUWANIE KONTAKTU");
        System.out.println("menu glowne --> usuwanie kontaktu");
        System.out.println("Podaj hash kontaktu: ");
        hash = Menu.readResponseString();
        System.out.println("\n\n");
    }

    @Override
    /**
     * Shows removing contact, and remove it. List without contacts is save by automatic
     */
    public void execute() {
        Integer contactID = ContactManager.getContactIDFromHash(hash);
        if (contactID == -1){
            System.out.println("Nie ma takiego kontaktu");
        }
        else {
            System.out.println("Usuwany kontakt: ");
            System.out.println(
                    ContactManager.getContact(contactID));
            ContactManager.deleteContact(hash);
        }
        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    /**
     * Interaction with user is ended only if user specified some hash to delete
     */ public Boolean shouldBreak() {
        return !hash.equals("");
    }
}
