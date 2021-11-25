import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The HotelInitialiser class reads hotel and room data from a specified file, creates appropriate Hotel and TypeOfRoom objects and
 * stores them in a list to be further processed later on.
 *
 * @author Edison Cai 20241135
 */
public abstract class HotelInitialiser {
    protected final static int HOTEL_INDEX = 0;
    protected final static int ROOM_INDEX = 1;
    protected final static int NUMBER_OF_ROOMS_INDEX = 2;
    protected final static int OCCUPANCY_MIN_INDEX = 3;
    protected final static int OCCUPANCY_MAX_INDEX = 4;
    protected final static int RATES_START_INDEX = 5;
    protected final static int RATES_END_INDEX = 11;

    public static ArrayList<Hotel> allHotels;

    /**
     * This method splits every line of the hotel details into cells of Strings.
     *
     * @param fileName The name of the csv file containing hotel and rooms details.
     * @return A list of lines that were in the file specified by fileName.
     * Each line is represented as an array of Strings, each representing a value of the hotel/room.
     */
    public static ArrayList<String[]> getFileCells(String fileName) {
        ArrayList<String[]> result = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] cells = reader.nextLine().split(",");
                result.add(cells);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File not found");
        }

        return result;
    }

    /**
     * Converts rates of a hotel room from a String array to an integer array.
     *
     * @param rates Rates of a room in Strings.
     * @return Rates of a room in integers.
     */
    private static double[] getRates(String[] rates) {
        double[] doubleRates = new double[rates.length];

        for (int i = 0; i < rates.length; i++) {
            doubleRates[i] = Double.parseDouble(rates[i]);
        }

        return doubleRates;
    }

    /**
     * Initialises all the Hotels with all their and places them in an ArrayList.
     *
     * @param cells A list of lines represented as String arrays containing all hotel and room details and values.
     * @return An ArrayList of all Hotel objects that can be created from "cells".
     */
    public static ArrayList<Hotel> initialise(ArrayList<String[]> cells) {
        ArrayList<Hotel> result = new ArrayList<>();

        // Going through every line.
        for (int i = 0; i < cells.size(); i++) {
            // Getting a single line as an array of Strings.
            // This variable is only used for brevity.
            String[] line = cells.get(i);

            // Once we reach a line where a new hotel is defined,
            // make a Hotel object and start adding room types.
            // We know a new hotel is defined when it has 12 values and the first value is not "".
            if (line.length == 12 && !line[HOTEL_INDEX].equals("")) {
                // Create a new Hotel instance with the name that is specified in the HOTEL_INDEX of "line".
                Hotel newHotel = new Hotel(line[HOTEL_INDEX]);

                // Get the first room defined in the hotel and add it to newHotel.
                TypeOfRoom newRoom = new TypeOfRoom(line[ROOM_INDEX], Integer.parseInt(line[OCCUPANCY_MIN_INDEX]),
                        Integer.parseInt(line[OCCUPANCY_MAX_INDEX]),
                        getRates(Arrays.copyOfRange(line, RATES_START_INDEX, RATES_END_INDEX + 1)));
                newHotel.addTypeOfRoom(newRoom);

                i++;

                // Keep adding rooms to the hotel until we reach another hotel
                while (cells.get(i)[HOTEL_INDEX].equals("")) {
                    line = cells.get(i);
                    newRoom = new TypeOfRoom(line[ROOM_INDEX], Integer.parseInt(line[OCCUPANCY_MIN_INDEX]),
                            Integer.parseInt(line[OCCUPANCY_MAX_INDEX]),
                            getRates(Arrays.copyOfRange(line, RATES_START_INDEX, RATES_END_INDEX + 1)));
                    newHotel.addTypeOfRoom(newRoom);
                    i++;

                    // If we reach the last line, add newHotel to the list of hotels and return.
                    if (i == cells.size()) {
                        result.add(newHotel);
                        return result;
                    }
                }

                // If the line we're on comes across another hotel, we are done with the current hotel.
                // Add the hotel to the list, decrement i(since the loop with increment it again) and repeat the process
                // until all the hotels are added.
                if (!cells.get(i)[HOTEL_INDEX].equals("")) {
                    i--;
                }
                result.add(newHotel);
            }
        }
        return result;
    }
}
