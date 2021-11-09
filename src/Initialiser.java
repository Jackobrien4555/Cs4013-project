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
    protected final int HOTEL_INDEX = 0;
    protected final int ROOM_INDEX = 1;
    protected final int NUMBER_OF_ROOMS_INDEX = 2;
    protected final int OCCUPANCY_MIN_INDEX = 3;
    protected final int OCCUPANCY_MAX_INDEX = 4;
    protected final int RATES_START_INDEX = 5;
    protected final int RATES_END_INDEX = 11;

    /**
     * @param fileName
     * @return A list of lines that were in the file specified by fileName
     * Each line is represented as an array of Strings, each representing a value of the hotel/room
     *
     */
    public static ArrayList<String[]> getFileCells(String fileName) {
        ArrayList<String[]> result = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] cells = reader.nextLine().split(",");
                result.add(cells);
                //System.out.println(Arrays.toString(cells));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File not found");
        }

        return result;
    }

    public abstract Object initialise(ArrayList<String[]> cells);

    protected int[] getRates(String[] rates){
        int[] intRates = new int[rates.length];

        for(int i = 0; i < rates.length; i++){
            intRates[i] = Integer.parseInt(rates[i]);
        }

        return intRates;
    }
}
