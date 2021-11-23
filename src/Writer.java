import java.io.FileWriter;
import java.io.IOException;

/**
 * The Writer class writes both reservations and cancellations
 * @author David Walsh 20276885
 */

public class Writer {
    // Write to a specific csv
    // refunded = 0, it is refunded

    public void writeReservation(String filename, Reservation reservation) {
        String newReservation = String.format("%d, %s, %s, %s, %s, %d, %s, %f", reservation.getResNumber(), reservation.getResName(), reservation.getResType(),
                reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getNumberOfRooms(), reservation.getRooms(), reservation.getTotalCost());

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(newReservation);

            writer.close();
            System.out.println("Wrote to file.");
        }
        catch (IOException exception) {
            System.out.println("Error: Did not write file");
            exception.printStackTrace();
        }
    }

    public void writeCancellation(String filename, Cancellation cancellation) {
        String newCancellation = String.format("%d, %s, %s, %s, %s, %d, %s, %f, %s, %f", cancellation.getReservation().getResNumber(), cancellation.getReservation().getResName(), cancellation.getReservation().getResType(),
                cancellation.getReservation().getCheckInDate(), cancellation.getReservation().getCheckOutDate(), cancellation.getReservation().getNumberOfRooms(), cancellation.getReservation().getRooms(),
                cancellation.getReservation().getTotalCost(), cancellation.getCancellationDate(), cancellation.getIncome());

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(newCancellation);

            writer.close();
            System.out.println("Wrote to file.");
        }
        catch (IOException exception) {
            System.out.println("Error: Did not write file");
            exception.printStackTrace();
        }
    }
}
