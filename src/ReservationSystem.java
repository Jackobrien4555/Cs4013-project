import java.util.ArrayList;

public class ReservationSystem {

    private InputScanner userInput;
    private PrintedMenus menus;
    private Reader reader;

    public ReservationSystem() {
        menus = new PrintedMenus();
        userInput = new InputScanner();
        Reader.readReservations(ConstantReferences.RESERVATIONS);
        Reader.readCancellations(ConstantReferences.CANCELLATIONS);
        HotelInitialiser.initialise(HotelInitialiser.getFileCells(ConstantReferences.HOTELS));
    }

    public void run() {
        int choice;

        menus.printStartUpMenu();
        choice = userInput.getStartUpChoice();

        while(choice != ConstantReferences.EXIT_STARTUP) {
            if(choice == 1) {
                subMenuRun(ConstantReferences.EXIT_CUSTOMER);
            } else if(choice == 2) {
                subMenuRun(ConstantReferences.EXIT_ADMINISTRATOR);
            }
            menus.printStartUpMenu();
            choice = userInput.getStartUpChoice();
        }
        System.out.println("\n---- QUITTING THE PROGRAM ----");
    }

    private void subMenuRun(int exitValue) {
        Writer writer = new Writer();
        int choice = 0;
        printRightMenu(exitValue);

        choice = userInput.getUserMenuChoice(exitValue);
        while(choice != exitValue) {
            if(choice == 1) {
                writer.writeReservation(ConstantReferences.RESERVATIONS, userInput.readReservation());
            } else if(choice == 2) {
                writer.writeCancellation(ConstantReferences.CANCELLATIONS, userInput.readValidCancellation(reader));
            } else if(choice == 3) {
                int size = Reader.getAllReservations().size();
                for(int i = 0; i < size; i++) {
                    System.out.println(Reader.getAllReservations().get(i).toString());
                }
            } else if(choice == 4) {
                int size = Reader.getAllCancellations().size();
                for(int i = 0; i < size; i++) {
                    System.out.println(Reader.getAllCancellations().get(i).toString());
                }
            } else if(choice == 5) {
                displayAnalyticalMenu();
            }
            printRightMenu(exitValue);
            choice = userInput.getUserMenuChoice(exitValue);
        }

    }

    private void displayAnalyticalMenu() {
        int choice = 0;
        menus.printAnalyticsMenu();
        choice = userInput.getAnalyticsChoice();
        while (choice != ConstantReferences.EXIT_ANALYTICAL) {
            if(choice == 1) {
                // code
            } else if (choice == 2) {
                // code
            } else if (choice == 3) {
                // code
            }
            menus.printAnalyticsMenu();
            choice = userInput.getAnalyticsChoice();
        }
    }

    private void printRightMenu(int exitValue) {
        if(exitValue == ConstantReferences.EXIT_CUSTOMER) {
            menus.printCustomerMenu();
        } else if (exitValue == ConstantReferences.EXIT_ADMINISTRATOR) {
            menus.printAdministratorMenu();
        }
    }

}
