package cloud.ptl.menu;

import cloud.ptl.persistence.Contact;
import cloud.ptl.persistence.ContactManager;

import java.util.ArrayList;

/**
 * Class works this same as Searching by Name
 * @see FindByNameMenu
 */
public class FindBySurnameMenu extends Menu {
    private String userResponse;

    public FindBySurnameMenu(){
        userResponse = "";
    }

    @Override
    public void show() {
        System.out.println("WYSZUKIWANIE KONTAKTOW PO NAZWISKU");
        System.out.println("menu glowne --> wyszukiwanie kontaktow --> po nazwisku");
        System.out.println("Podaj nazwisko: ");
        userResponse = Menu.readResponseString();

        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    public void execute() {
        ArrayList<Contact> res = ContactManager.findBySurname(userResponse);
        if(res.size() > 0) {
            for (Contact c : res) {
                System.out.println(c);
                System.out.println();
            }
        }
        else{
            System.out.println("Brak kontakotw o podanym nazwisku");
        }
        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    public Boolean shouldBreak() {
        return !userResponse.equals("");
    }
}
