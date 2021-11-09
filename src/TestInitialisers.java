import java.util.ArrayList;

public class TestInitialisers {
    public static void main(String[] args) {
        HotelInitialiser test = new HotelInitialiser();

        ArrayList<String[]> cells = test.getFileCells("l4Hotels.csv");
        ArrayList<Hotel> hotels = test.initialise(cells);
        System.out.println(hotels);
        return;
    }
}
