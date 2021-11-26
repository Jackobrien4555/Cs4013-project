/**
 * This class uses InputScanner, PrintedMenus and Reader along with ConstantReferences to create menus and
 * sub menus so that the user can use all the different methods we have created.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */

import java.time.LocalDate;
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

    /**
     * This is the main run function which helps run the startup menu. From this menu everything else
     * gets run based on the user's selection. Without the run method nothing can be used by the user.
     */
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
        System.out.println("\n__________                                             ________        .__  __    __  .__                ");
        System.out.println("\\______   \\_______  ____   ________________    _____   \\_____  \\  __ __|__|/  |__/  |_|__| ____    ____  ");
        System.out.println(" |     ___/\\_  __ \\/  _ \\ / ___\\_  __ \\__  \\  /     \\   /  / \\  \\|  |  \\  \\   __\\   __\\  |/    \\  / ___\\ ");
        System.out.println(" |    |     |  | \\(  <_> ) /_/  >  | \\// __ \\|  Y Y  \\ /   \\_/.  \\  |  /  ||  |  |  | |  |   |  \\/ /_/  >");
        System.out.println(" |____|     |__|   \\____/\\___  /|__|  (____  /__|_|  / \\_____\\ \\_/____/|__||__|  |__| |__|___|  /\\___  / ");
        System.out.println("                        /_____/            \\/      \\/         \\__>                            \\//_____/  ");
        System.out.println("                         Edison Cai, Sergiu Mereacre, Jack O'Brien, David Walsh                            ");
    }

    /**
     * This is a sub menu of the startup menu which helps execute functions and methods depending on
     * the user's selection.
     * @param exitValue The exit value of the chose menu.
     */
    private void subMenuRun(int exitValue) {
        Writer writer = new Writer();
        int choice = 0;
        printRightMenu(exitValue);

        choice = userInput.getUserMenuChoice(exitValue);
        while(choice != exitValue) {
            if(choice == 1) {
                writer.writeReservation(ConstantReferences.RESERVATIONS, userInput.readReservation());
            } else if(choice == 2) {
                Cancellation cancellation = userInput.readValidCancellation();
                int resNum = cancellation.getReservation().getResNumber();
                writer.writeCancellation(ConstantReferences.CANCELLATIONS, cancellation);
                ArrayList<Reservation> reservations = ReservationCancellationManager.getAllReservations();
                reservations.remove(ReservationCancellationManager.getReservation(resNum));
                ReservationCancellationManager.setAllReservations(reservations);
                writer.writeReservations(ConstantReferences.RESERVATIONS, reservations);
            } else if(choice == 3) {
                int size = ReservationCancellationManager.getAllReservations().size();
                for(int i = 0; i < size; i++) {
                    System.out.println(ReservationCancellationManager.getAllReservations().get(i).toString());
                }
            } else if(choice == 4) {
                int size = ReservationCancellationManager.getAllCancellations().size();
                for(int i = 0; i < size; i++) {
                    System.out.println(ReservationCancellationManager.getAllCancellations().get(i).toString());
                }
            } else if(choice == 5) {
                displayAnalyticalMenu();
            }
            printRightMenu(exitValue);
            choice = userInput.getUserMenuChoice(exitValue);
        }

    }

    /**
     * Co-Author: Edison Cai.
     * This displays the analytical menu and includes all the different methods it might have to run based on the
     * user's input.
     */
    private void displayAnalyticalMenu() {
        int choice = 0;
        menus.printAnalyticsMenu();
        choice = userInput.getAnalyticsChoice();
        while (choice != ConstantReferences.EXIT_ANALYTICAL) {
            if(choice == 1) {
                System.out.println("Do you also want to show rooms that aren't booked? Y/N");
                boolean showRoom = userInput.getYesOrNo();
                System.out.print("Enter the starting date: ");
                LocalDate dateCheckIn = userInput.getDate();
                System.out.print("Enter the ending date: ");
                LocalDate dateCheckOut = userInput.getDate();
                while(dateCheckIn.compareTo(dateCheckOut) > 0) {
                    System.out.print("You cannot have the starting date be later than the ending date, try again: ");
                    dateCheckIn = userInput.getDate();
                }
                System.out.println(DataAnalysis.getOccupancyRatesAll(ReservationCancellationManager.getAllReservations(), dateCheckIn, dateCheckOut, showRoom));
            } else if (choice == 2) {
                System.out.println("Do you also want to show rooms that aren't booked? Y/N");
                boolean showRoom = userInput.getYesOrNo();
                System.out.print("Enter the starting date: ");
                LocalDate dateCheckIn = userInput.getDate();
                System.out.print("Enter the ending date: ");
                LocalDate dateCheckOut = userInput.getDate();
                while(dateCheckIn.compareTo(dateCheckOut) > 0) {
                    System.out.print("You cannot have the starting date be later than the ending date, try again: ");
                    dateCheckIn = userInput.getDate();
                }
                System.out.println(DataAnalysis.calculateIncomeAll(ReservationCancellationManager.getAllReservations(), ReservationCancellationManager.getAllCancellations(), dateCheckIn, dateCheckOut, showRoom));

            }
            menus.printAnalyticsMenu();
            choice = userInput.getAnalyticsChoice();
        }
    }

    /**
     * Method helps to pick between the customer and administrator depending on the exit value.
     */
    private void printRightMenu(int exitValue) {
        if(exitValue == ConstantReferences.EXIT_CUSTOMER) {
            menus.printCustomerMenu();
        } else if (exitValue == ConstantReferences.EXIT_ADMINISTRATOR) {
            menus.printAdministratorMenu();
        }
    }

}
