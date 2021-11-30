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

    private final InputScanner userInput;
    private final PrintedMenus menus;

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

        while (choice != ConstantReferences.EXIT_STARTUP) {
            if (choice == 1) {
                subMenuRun(ConstantReferences.EXIT_CUSTOMER);
            } else if (choice == 2) {
                System.out.print("Username (-1 to quit): ");
                String username = userInput.getUsername();
                if (username == null) {
                    choice = 0;
                    continue;
                }
                System.out.print("Password (-1 to quit): ");
                String password = userInput.getPassword(username);
                if (password == null) {
                    choice = 0;
                    continue;
                }
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
     *
     * @param exitValue The exit value of the chose menu.
     */
    private void subMenuRun(int exitValue) {
        Writer writer = new Writer();
        int choice;
        printRightMenu(exitValue);

        choice = userInput.getUserMenuChoice(exitValue);
        while (choice != exitValue) {
            if (choice == 1) {
                Reservation reservationToBeAdded = userInput.readReservation();
                if (reservationToBeAdded != null) {
                    writer.writeReservation(ConstantReferences.RESERVATIONS, reservationToBeAdded);
                }
            } else if (choice == 2) {
                Cancellation cancellation = userInput.readValidCancellation();

                if (cancellation != null) {
                    ReservationCancellationManager.addCancellation(cancellation);
                    int resNum = cancellation.getReservation().getResNumber();
                    writer.writeCancellation(ConstantReferences.CANCELLATIONS, cancellation);
                    ArrayList<Reservation> reservations = ReservationCancellationManager.getAllReservations();
                    reservations.remove(ReservationCancellationManager.getReservation(resNum));
                    ReservationCancellationManager.setAllReservations(reservations);
                    writer.writeReservations(ConstantReferences.RESERVATIONS, reservations);

                }

            } else if (choice == 3) {
                ArrayList<Reservation> reservations = ReservationCancellationManager.getAllReservations();
                for (Reservation reservation : reservations) {
                    System.out.println();
                    System.out.println(reservation.reservationFormat());
                }
            } else if (choice == 4) {
                ArrayList<Cancellation> cancellations = ReservationCancellationManager.getAllCancellations();
                for (Cancellation cancellation : cancellations) {
                    System.out.println();
                    System.out.println(cancellation.cancellationFormat());
                }
            } else if (choice == 5) {
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
        int choice;
        menus.printAnalyticsMenu();
        choice = userInput.getAnalyticsChoice();
        while (choice != ConstantReferences.EXIT_ANALYTICAL) {
            if (choice == 1) {
                System.out.print("Do you also want to show rooms that aren't booked? Y/N (-1 to quit): ");

                // -1 means return, 0 means false, 1 means true.
                int showRoomIndicator = userInput.getYesOrNo();
                boolean showRoom = false;

                if (showRoomIndicator != -1) {
                    if (showRoomIndicator == 1) {
                        showRoom = true;
                    }

                    System.out.print("Enter the starting date (YYYY-MM-DD) (-1 to quit): ");
                    LocalDate dateCheckIn = userInput.getDate();
                    if (dateCheckIn == null) {
                        continue;
                    }

                    System.out.print("Enter the ending date (YYYY-MM-DD) (-1 to quit): ");
                    LocalDate dateCheckOut = userInput.getDate();
                    if (dateCheckOut == null) {
                        continue;
                    }

                    while (dateCheckIn.compareTo(dateCheckOut) > 0) {
                        System.out.print("You cannot have the starting date be later than the ending date, try again (-1 to quit): ");
                        dateCheckIn = userInput.getDate();
                    }
                    DataAnalysis.printAnalytics(DataAnalysis.getOccupancyRatesAll(ReservationCancellationManager.getAllReservations(), dateCheckIn, dateCheckOut, showRoom));

                }


            } else if (choice == 2) {
                System.out.print("Do you also want to show rooms that aren't booked? Y/N (-1 to quit): ");
                int showRoomIndicator = userInput.getYesOrNo();
                boolean showRoom = false;

                if (showRoomIndicator != -1) {
                    if (showRoomIndicator == 1) {
                        showRoom = true;
                    }
                    System.out.print("Enter the starting date (YYYY-MM-DD) (-1 to quit): ");
                    LocalDate dateCheckIn = userInput.getDate();
                    if (dateCheckIn == null) {
                        continue;
                    }

                    System.out.print("Enter the ending date (YYYY-MM-DD) (-1 to quit): ");
                    LocalDate dateCheckOut = userInput.getDate();
                    if (dateCheckOut == null) {
                        continue;
                    }

                    while (dateCheckIn.compareTo(dateCheckOut) > 0) {
                        System.out.print("You cannot have the starting date be later than the ending date, try again (-1 to quit): ");
                        dateCheckIn = userInput.getDate();
                    }
                    DataAnalysis.printAnalytics(DataAnalysis.calculateIncomeAll(ReservationCancellationManager.getAllReservations(), ReservationCancellationManager.getAllCancellations(), dateCheckIn, dateCheckOut, showRoom));

                }

            }
            menus.printAnalyticsMenu();
            choice = userInput.getAnalyticsChoice();
        }
    }

    /**
     * Method helps to pick between the customer and administrator depending on the exit value.
     */
    private void printRightMenu(int exitValue) {
        if (exitValue == ConstantReferences.EXIT_CUSTOMER) {
            menus.printCustomerMenu();
        } else if (exitValue == ConstantReferences.EXIT_ADMINISTRATOR) {
            menus.printAdministratorMenu();
        }
    }

}
