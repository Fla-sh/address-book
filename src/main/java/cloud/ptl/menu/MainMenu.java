package cloud.ptl.menu;

/**
 * First menu which encounters user. Has options for searching, adding, modifying, deleting contact in Contact Book
 */
public class MainMenu extends Menu {
    private Integer response;

    public MainMenu(){
        response = 0;
    }

    @Override
    /**
     * Asks user what he/she want to do
     */
    public void show() {
        System.out.println("GLOWNE MENU");
        System.out.println("[1] Szukaj kontaktu");
        System.out.println("[2] Dodaj kontakt");
        System.out.println("[3] Modyfikuj kontakt");
        System.out.println("[4] Usun kontakt");
        System.out.println("[5] Zakoncz");

        response = Menu.readResponseInteger(false, 1,5);
        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("\n\n");
    }

    @Override
    /**
     * Starts proper menu for given task
     */
    public void execute() {
        switch(response){
            case 1:{
                Menu menu = new ContactSearchMenu();
                while(!menu.shouldBreak()) {
                    menu.show();
                    menu.execute();
                }
                break;
            }
            case 2:{
                Menu menu = new AddContactMenu();
                while(!menu.shouldBreak()){
                    menu.show();
                    menu.execute();
                }
                break;
            }
            case 3:{
                Menu menu = new ModifyContactMenu();
                while(!menu.shouldBreak()){
                    menu.show();
                    menu.execute();
                }
                break;
            }
            case 4:{
                Menu menu = new RemoveContactMenu();
                while(!menu.shouldBreak()){
                    menu.show();
                    menu.execute();
                }
                break;
            }
        }
    }

    @Override
    /**
     * Interaction with user is ended if given response was 5
     */
    public Boolean shouldBreak() {
        return response == 5;
    }
}
