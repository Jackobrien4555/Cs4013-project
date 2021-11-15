/**
 * This class is necessary to print the menu that allows the user to see which
 * options one might have.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */

public class PrintedMenus {

    /**
     * First menu that gives the options for each submenu.
     * @return Printed StartUp menu.
     */
    public void printStartUpMenu() {
        String[] options = {"\n------STARTUP MENU------",
                "1. Show all reservations.",
                "2. Make a reservation.",
                "3. Cancel a reservation.",
                "4. Data analysis.",
                "5. Quit the program.",
                "------------------------"};
        printMenu(options);
    }

    /**
     * Analytical menu that gives options for different sections.
     * @return Printed Analytics menu.
     */
    public void printAnalyticsMenu() {
        String[] options = {"\n-------ANALYTICS MENU-------",
                "1. Hotel occupancy analytics.",
                "2. Room occupancy analytics.",
                "3. Financial analytics.",
                "4. Go back to startup menu.",
                "----------------------------"};
        printMenu(options);
    }

    /**
     * Method that prints out each String needed for the user.
     * @param options String arraylist given.
     * @return Printed string arraylist.
     */
    private void printMenu(String[] options) {
        for(int i = 0; i < options.length; i++) {
            if(i == options.length - 1) {
                System.out.print(options[i]);
            } else {
                System.out.println(options[i]);
            }
        }
    }

}
