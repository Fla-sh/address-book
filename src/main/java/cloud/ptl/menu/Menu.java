package cloud.ptl.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Menu class, is general skeleton for creation unified menus. Each subclass menu has show and execute method.
 * If menu has chose from dialog, show method should only show available options and collect input.
 * If menu has not chose from dialog, then show method should collect needed input for execute method.
 * Although execute method can gather information from user, but only if it's needed to modify or add some entries
 */
public abstract class Menu {
    /**
     * Method showing available options, could have chose from dialog, or could ask user for information important for execute method
     */
    public abstract void show();

    /**
     * Executes menu task, can gather information from user
     */
    public abstract void execute();

    /**
     * Check if interaction with menu is completed
     * @return true if interaction is ended, and false if not
     */
    public abstract Boolean shouldBreak();

    /**
     * Reads user response from standard input, and check if data is Integer, if not ask user to retype answer
     * @param isNullable true if user response can be blank
     * @return user response as integer
     */
    static Integer readResponseInteger(boolean isNullable) {
        System.out.print("--> ");
        int response = -1;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean isSet = false;
        while(!isSet) {
            try {
                response = Integer.parseInt(
                        bufferedReader.readLine());
                isSet = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException ex) {
                if(!isNullable) {
                    System.out.println("\nPodana wartosc nie jest liczba");
                    System.out.print("--> ");
                }
                else{
                    isSet = true;
                }
            }
        }
        return response;
    }

    /**
     * Works same as readResponseInteger, but check if user typed is in between given set
     * @param isNullable true if user response can be blank
     * @param minElem lowest accepted value (inclusive)
     * @param maxElem highest accepted value (inclusive)
     * @return user response as integer
     */
    static Integer readResponseInteger(boolean isNullable, int minElem, int maxElem) {
        Integer res = readResponseInteger(isNullable);
        if(!isNullable) {
            while (res < minElem || res > maxElem) {
                System.out.println("Podana wartosc jest niedopuszczalna\n");
                res = readResponseInteger(false);
            }
        }
        return res;
    }

    /**
     * Reads user response from standard input, and treat it as String
     * @return user response
     */
    static String readResponseString() {
        String response = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            response = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
