import java.io.File;

/**
 * This is a class for testing purposes only.
 * It randomly generates a set number of reservations and add them to a test file.
 * @author 20241135
 */
public class RandomReservationsGenerator {
    private static int amount = 1;

    public static void main(String[] args) {
        File destinationFile = new File("../randomReservations.csv");

        for(int i = 0; i < amount; i++){
            System.out.println(destinationFile.getName());
        }
    }
}
