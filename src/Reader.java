import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The reader classes has methods that will read the contents of files
 * and convert them into Reservation and/or Cancellation objects.
 *
 * @author Edison Cai 20241135
 */
public abstract class Reader {

    /**
     * Reads a file line-by-line, creates a Reservation object for
     * all of them. They are placed in an ArrayList and will be passed into
     * the ReservationCancellationManager.setAllReservations() method.
     *
     * @param filepath The file path of the file to be read from.
     */
    public static void readReservations(String filepath) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                // Splitting each line in the file into different values and placing them in the "cells" array.
                String[] cells = reader.nextLine().split(",");

                // Convert the values in "cells" to a Reservation object and add it to "reservations".
                reservations.add(lineToReservation(cells));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File containing reservations not found at: " + filepath);
        }

        ReservationCancellationManager.setAllReservations(reservations);
    }

    /**
     * Reads a file line-by-line, creates a Cancellation object for
     * all of them. They are placed in an ArrayList and will be passed into
     * the ReservationCancellationManager.setAllCancellations() method.
     *
     * @param filepath The file path of the file to be read from.
     */
    public static void readCancellations(String filepath) {
        ArrayList<Cancellation> cancellations = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                // Preparing the reservation part of the cancellation.
                Reservation reservation;
                String[] cells = reader.nextLine().split(",");

                // Getting some extra values that would be present in a cancellation.
                LocalDate cancellationDate = LocalDate.parse(cells[cells.length - 2]);
                double income = Double.parseDouble(cells[cells.length - 1]);

                // Get the reservation part of the cancellation by getting a subarray of "cells".
                // Using this subarray to create a Reservation object.
                String[] reservationCells = Arrays.copyOfRange(cells, 0, cells.length - 2);
                reservation = lineToReservation(reservationCells);

                // Add a new Cancellation with its Reservation, cancellation date and income to "cancellations".
                cancellations.add(new Cancellation(reservation, cancellationDate, income));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File containing cancellations not found at: " + filepath);
        }

        ReservationCancellationManager.setAllCancellations(cancellations);
    }

    /**
     * Reading list of admins. They are placed in an ArrayList and will be passed into
     * the ReservationCancellationManager.setAllUsers() method.
     *
     * @param filepath File path to the list of admin usernames and passwords.
     * @return A list of User objects gathered from the filepath.
     */
    public static ArrayList<User> readUsers(String filepath) {
        ArrayList<User> users = new ArrayList<>();

        try {
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                // Preparing the user.
                String[] cells = reader.nextLine().split(",");

                String name = cells[0];
                String password = cells[1];
                users.add(new User(name, password));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File containing admin details not found at: " + filepath);
        }

        ReservationCancellationManager.setAllUsers(users);
        return users;
    }

    /*
     * By taking in an array of Strings as values for a Reservation, a new Reservation
     * instance is created and returned.
     */
    private static Reservation lineToReservation(String[] cells) {
        // Converting all values in "cells" to their appropriate types.
        int resNumber = Integer.parseInt(cells[0]);
        String resName = cells[1];
        String resType = cells[2];
        LocalDate checkInDate = LocalDate.parse(cells[3]);
        LocalDate checkOutDate = LocalDate.parse(cells[4]);
        int numberOfRooms = Integer.parseInt(cells[5]);

        // Initialising a list of Room objects to be stored in the Reservation instance.
        ArrayList<Room> rooms = new ArrayList<>();

        // Add all the rooms in a reservation one-by-one.
        for (int i = 6; i < cells.length - 1; i += 2) {
            // cells[i] is the room type and Integer.parseInt(cells[i + 1]) will be the occupancy.
            Room room = new Room(cells[i], Integer.parseInt(cells[i + 1]));
            rooms.add(room);
        }

        double totalCost = Double.parseDouble(cells[cells.length - 1]);

        // Create the new Reservation after parsing "cells" and returning it.
        return new Reservation(resNumber, resName, resType, checkInDate, checkOutDate,
                numberOfRooms, rooms, totalCost);
    }


}
