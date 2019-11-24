package cloud.ptl;

import cloud.ptl.menu.MainMenu;
import cloud.ptl.menu.Menu;
import cloud.ptl.persistence.ContactManager;

@SuppressWarnings("WeakerAccess")
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Menu menu = new MainMenu();
        ContactManager.intalize();
        while(!menu.shouldBreak()){
            menu.show();
            menu.execute();
        }
    }
}
