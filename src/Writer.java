import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    // Write to a specific csv
    // refunded = 0, it is refunded

    public void writeReservation(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write();

            writer.close();
            System.out.println("Wrote to file.");
        }
        catch (IOException exception) {
            System.out.println("Error: Did not write file");
            exception.printStackTrace();
        }
    }

    public void writeCancellation(String filename) {

    }
}
