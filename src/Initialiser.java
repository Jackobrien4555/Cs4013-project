import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Initialiser class acts as the superclass for the HotelInitialiser and RoomInitialiser classes
 * It reads hotel and room data from a specified file, creates appropriate Hotel and Room objects and
 * stores them in a list to be further processes later on
 *
 * @author 20241135
 */
public abstract class Initialiser {

    /**
     * @param fileName
     * @return A list of lines that were in the file specified by fileName
     * Each line is represented as an array of Strings, each representing a value of the hotel/room
     *
     */
    public ArrayList<String[]> getFileCells(String fileName) {
        ArrayList<String[]> result = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] cells = reader.nextLine().split(",");
                result.add(cells);
                System.out.println(Arrays.toString(cells));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File not found");
        }

        return result;
    }
}
