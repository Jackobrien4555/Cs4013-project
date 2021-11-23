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
public class Reader {

    /**
     * Reads a file line-by-line, creates a Reservation object for
     * all of them. They are placed in an ArrayList and returned.
     *
     * @param filepath The file path of the file to be read from.
     * @return A list of Reservations extracted from the specified file path.
     */
    public ArrayList<Reservation> readReservations(String filepath) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] cells = reader.nextLine().split(",");

                reservations.add(lineToReservation(cells));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File not found");
        }

        return reservations;
    }

    ArrayList<Cancellation> readCancellations(String filepath) {
        ArrayList<Cancellation> cancellations = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                Reservation reservation = null;
                String[] cells = reader.nextLine().split(",");
                LocalDate cancellationDate = LocalDate.parse(cells[cells.length - 2]);
                double income = Double.parseDouble(cells[cells.length - 1]);
                boolean refunded = income == 0;

                // Get the "reservation" part of the cancellation
                String[] reservationCells = Arrays.copyOfRange(cells, 0, cells.length - 2);
                reservation = lineToReservation(reservationCells);

                cancellations.add(new Cancellation(reservation, cancellationDate, refunded, income));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File not found");
        }

        return cancellations;
    }

    private Reservation lineToReservation(String[] cells) {
        int resNumber = Integer.parseInt(cells[0]);
        String resName = cells[1];
        String resType = cells[2];
        LocalDate checkInDate = LocalDate.parse(cells[3]);
        LocalDate checkOutDate = LocalDate.parse(cells[4]);
        int numberOfRooms = Integer.parseInt(cells[5]);
        ArrayList<Room> rooms = new ArrayList<>();

        for (int i = 5; i < cells.length - 1; i += 2) {
            Room room = new Room(cells[i], cells[i + 1], 0);
            rooms.add(room);
        }

        double totalCost = Double.parseDouble(cells[cells.length - 1]);

        return new Reservation(resNumber, resName, resType, checkInDate, checkOutDate,
                numberOfRooms, rooms, totalCost);
    }
}
