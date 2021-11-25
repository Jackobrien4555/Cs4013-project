/**
 * This class is necessary to print the menu that allows the user to see which
 * options one might have.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */

public class PrintedMenus {

    /**
     * Menu that represents the startup login choices.
     * @return Printed StartUp menu.
     */
    public void printStartUpMenu() {
        String[] options = {"\n------ STARTUP MENU ------",
                            "1. Customer Login.",
                            "2. Administrator Login.",
                            "3. Quit the program.",
                            "--------------------------",
                            "Please enter choice: "};
        printMenu(options);
    }

    /**
     * Menu that represents the customer login.
     * @return Printed Customer menu.
     */
    public void printCustomerMenu() {
        String[] options = {"\n----- CUSTOMER MENU -----",
                            "1. Make a reservation.",
                            "2. Cancel a reservation.",
                            "3. Go back to login menu.",
                            "--------------------------",
                            "Please enter choice: "};
        printMenu(options);
    }

    /**
     * Administrator menu that gives options that aren't available in the customer menu.
     * @return Printed Administrator menu.
     */
    public void printAdministratorMenu() {
        String[] options = {"\n----- ADMINISTRATOR MENU -----",
                            "1. Make a reservation.",
                            "2. Cancel a reservation.",
                            "3. Show all reservations.",
                            "4. Show all cancellations.",
                            "5. Data analysis.",
                            "6. Go back to login menu.",
                            "------------------------------",
                            "Please enter choice: "};
        printMenu(options);
    }

    /**
     * Analytical menu that gives options for different sections.
     * @return Printed Analytics menu.
     */
    public void printAnalyticsMenu() {
        String[] options = {"\n------- ANALYTICS MENU -------",
                "1. Hotel occupancy analytics.",
                //"2. Room occupancy analytics.",
                "2. Financial analytics.",
                "3. Go back to previous menu.",
                "------------------------------",
                "Please enter choice: "};
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
