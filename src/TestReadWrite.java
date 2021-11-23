public class TestReadWrite {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String readRes = "reservations/random_res.csv";
        String readCan = "reservations/cancellations.csv";
        reader.readReservations(readRes);
        reader.readCancellations(readCan);
        System.out.println();
    }
}
