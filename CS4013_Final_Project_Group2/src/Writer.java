import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Writer class writes both reservations and cancellations
 *
 * @author David Walsh 20276885
 */
public class Writer {

    /**
     * Writes a reservation into a specified file.
     *
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
     * Writes a reservation into a specified file.
     *
     * @param filename The file path of the file to be read from.
     * @param reservations The ArrayList of reservations to be written.
     */
    public void writeReservations(String filename, ArrayList<Reservation> reservations) {
        try {
            FileWriter writer = new FileWriter(filename, false);

            for (Reservation reservation : reservations) {
                writer.write(reservation.toString() + "\n");
            }

            writer.close();
        }
        catch (IOException exception) {
            System.out.println("Error: Did not write to file.");
            exception.printStackTrace();
        }

    }

    /**
     * Writes a cancellation into a specified file.
     *
     * @param filename The file path of the file to be read from.
     * @param cancellation The reservation to be written.
     */
    public void writeCancellation(String filename, Cancellation cancellation) {
        String newCancellation = cancellation.toString() + "\n";

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
