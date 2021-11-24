import java.time.LocalDate;
import java.util.ArrayList;

public class TestReadWrite {
    public static void main(String[] args) {
        Reader reader = new Reader();
        Writer writer = new Writer();

        ArrayList<Room> rooms = new ArrayList<Room>();
        Reservation myRes = new Reservation(4, "Gogh", "S", LocalDate.parse("2021-11-23"), LocalDate.parse("2021-11-23"), 1, rooms, 50.0);
        Cancellation myCan = new Cancellation(myRes);

        String readRes = "reservations/random_res.csv";
        String readCan = "reservations/cancellations.csv";
        String writeRes = "reservations/test_write_res.csv";
        String writeCan = "reservations/test_write_can.csv";

        ArrayList<Reservation> reservations = reader.readReservations(readRes);
        ArrayList<Cancellation> cancellations = reader.readCancellations(readCan);

        writer.writeReservation(writeRes, myRes);
        writer.writeCancellation(writeCan, myCan);

        System.out.println();
    }
}
