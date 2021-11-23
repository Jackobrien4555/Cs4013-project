import java.util.ArrayList;

public class TestReadWrite {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String readRes = "reservations/random_res.csv";
        String readCan = "reservations/cancellations.csv";
        ArrayList<Reservation> reservations = reader.readReservations(readRes);
        ArrayList<Cancellation> cancellations = reader.readCancellations(readCan);
        System.out.println();
    }
}
