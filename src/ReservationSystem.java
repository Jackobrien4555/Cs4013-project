public class ReservationSystem {

    private InputScanner userInput;
    private PrintedMenus menus;
    private ConstantReferences references;
    private Reader reader;
    private HotelInitialiser hotel;
    private DataAnalysis analysis;

    public ReservationSystem() {
        menus = new PrintedMenus();
        userInput = new InputScanner();
        reader.readReservations(references.RESERVATIONS);
        reader.readCancellations(references.CANCELLATIONS);
        hotel.initialise(HotelInitialiser.getFileCells(references.HOTELS));
    }

    public void run() {
        int choice;

        menus.printStartUpMenu();
        choice = userInput.getStartUpChoice();

        while(choice != references.EXIT_STARTUP) {
            if(choice == 1) {
                subMenuRun(references.EXIT_CUSTOMER);
            } else if(choice == 2) {
                subMenuRun(references.EXIT_ADMINISTRATOR);
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
                writer.writeReservation(references.RESERVATIONS, userInput.readReservation());
            } else if(choice == 2) {
                writer.writeCancellation(references.CANCELLATIONS, userInput.readValidCancellation(reader));
            } else if(choice == 3) {
                reader.getAllReservations().toString();
            } else if(choice == 4) {
                reader.getAllCancellations().toString();
            }
            printRightMenu(exitValue);
            choice = userInput.getUserMenuChoice(exitValue);
        }

    }

    private void printRightMenu(int exitValue) {
        if(exitValue == references.EXIT_CUSTOMER) {
            menus.printCustomerMenu();
        } else if (exitValue == references.EXIT_ADMINISTRATOR) {
            menus.printAdministratorMenu();
        }
    }

}
