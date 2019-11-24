package cloud.ptl.menu;

import cloud.ptl.persistence.ContactManager;

/**
 * Menu used to search some contact by its name, surname, or showing all contacts. Showing all contacts is not restricted in max
 * showed amount of contacts, so it could be problematic in huge Contact Book
 */
public class ContactSearchMenu extends Menu{
    private Integer response;

    public ContactSearchMenu(){
        response = 0;
    }

    @Override
    /**
     * Ask user to choose method of searching
     */
    public void show() {

        System.out.println("[1] Po imieniu");
        System.out.println("[2] Po nazwisku");
        System.out.println("[3] Pokaz wszystkie");
        System.out.println("[4] Cofnij");
        response = Menu.readResponseInteger(false, 1,4);

        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    /**
     * Searches by given method, and display results
     */
    public void execute() {
        switch (response) {
            case 1: {
                Menu menu = new FindByNameMenu();
                while(!menu.shouldBreak()) {
                    menu.show();
                    menu.execute();
                }
                break;
            }
            case 2: {
                Menu menu = new FindBySurnameMenu();
                while(!menu.shouldBreak()){
                    menu.show();
                    menu.execute();
                }
                break;
            }
            case 3:{
                for(int i = 0; i < ContactManager.size(); i++){
                    System.out.println(ContactManager.getContact(i));
                    System.out.print("\n\n");
                }

                System.out.println("------------------------------");
                System.out.println("\n\n");
            }
        }
    }

    @Override
    /**
     * Interaction with user is ended, if user chose option 4 in menu
     */
    public Boolean shouldBreak() {
        return response == 4;
    }
}
