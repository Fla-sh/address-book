package cloud.ptl.menu;

import cloud.ptl.persistence.Contact;
import cloud.ptl.persistence.ContactManager;

import java.util.ArrayList;

/**
 * Menu used to search contact by its name. Result can be list of contacts or nothing if contact with given name
 * not exist
 */
public class FindByNameMenu extends Menu {
    private String userResponse;

    public FindByNameMenu(){
        userResponse = "";
    }

    /**
     * Ask user for searched name
     */
    @Override
    public void show() {
        System.out.println("WYSZUKIWANIE KONTAKTOW PO IMIENIU");
        System.out.println("menu glowne --> wyszukiwanie kontaktow --> po imieniu");
        System.out.println("Podaj imie: ");
        userResponse = readResponseString();

        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    /**
     * Shows result of searching, or information telling that there is no contact with given name
     */
    public void execute() {
        ArrayList<Contact> res = ContactManager.findByName(userResponse);
        if(res.size() > 0) {
            for (Contact c : res) {
                System.out.println(c);
                System.out.println();
            }
        }
        else{
            System.out.println("Brak kontaktow o podanym imieniu");
        }
        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    /**
     * Interaction is ended only if user specified any name
     */
    public Boolean shouldBreak() {
        return !userResponse.equals("");
    }
}
