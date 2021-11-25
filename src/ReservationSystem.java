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
                for(int i = 0; i < Reader.getAllReservations().size(); i++) {
                    System.out.println(reader.getReservation(i).toString());
                }
            } else if(choice == 4) {
                reader.getAllCancellations().toString();
            }
            printRightMenu(exitValue);
            choice = userInput.getUserMenuChoice(exitValue);
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
