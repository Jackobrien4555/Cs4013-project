import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestDataAnalysis {
    public static void main(String[] args) {
        String filename = "reservations/random_res.csv";
        String hotelsFile = "l4Hotels.csv";

        String start_date = "2021-12-12";
        String end_date = "2021-12-23";

        ArrayList<String[]> reservations = new ArrayList<>();
        HotelInitialiser initialiser = new HotelInitialiser();

        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] cells = reader.nextLine().split(",");
                reservations.add(cells);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File not found");
        }

        DataAnalysis.getOccupancyRatesAll(reservations, start_date, end_date,
                initialiser.initialise(Initialiser.getFileCells(hotelsFile)));
    }
}
