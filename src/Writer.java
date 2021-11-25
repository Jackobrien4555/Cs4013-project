import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Writer class writes both reservations and cancellations
 * @author David Walsh 20276885
 */

public class Writer {

    /**
     * Writes a reservation into a specified file.
     * @param filename The file path of the file to be read from.
     * @param reservation The reservation to be written.
     */
    public void writeReservation(String filename, Reservation reservation) {

        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(reservation.toString() + "\n");
            writer.close();
        }
        catch (IOException exception) {
            System.out.println("Error: Did not write file");
            exception.printStackTrace();
        }
    }

    /**
     * Writes a cancellation into a specified file.
     * @param filename The file path of the file to be read from.
     * @param cancellation The reservation to be written.
     */
    public void writeCancellation(String filename, Cancellation cancellation) {
        // NEED TO DO, maybe create the file if the file doesn't exist
        String newCancellation = cancellation.toString();

        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(newCancellation);

            writer.close();
        }
        catch (IOException exception) {
            System.out.println("Error: Did not write file");
            exception.printStackTrace();
        }
    }
}
