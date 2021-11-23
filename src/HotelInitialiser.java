

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
public class HotelInitialiser {
    protected final int HOTEL_INDEX = 0;
    protected final int ROOM_INDEX = 1;
    protected final int NUMBER_OF_ROOMS_INDEX = 2;
    protected final int OCCUPANCY_MIN_INDEX = 3;
    protected final int OCCUPANCY_MAX_INDEX = 4;
    protected final int RATES_START_INDEX = 5;
    protected final int RATES_END_INDEX = 11;

    public static ArrayList<Hotel> allHotels;

    /**
     * This method splits every line of the hotel details into cells of Strings.
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
                //System.out.println(Arrays.toString(cells));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred: File not found");
        }

        return result;
    }

    /**
     * Converts rates of a hotel room from a String array to an integer array.
     * @param rates Rates of a room in Strings.
     * @return Rates of a room in integers.
     */
    private int[] getRates(String[] rates) {
        int[] intRates = new int[rates.length];

        for (int i = 0; i < rates.length; i++) {
            intRates[i] = Integer.parseInt(rates[i]);
        }

        return intRates;
    }

    /**
     * Initialises all the Hotels with all their and places them in an ArrayList.
     * @param cells A list of lines represented as String arrays containing all hotel and room details and values.
     * @return An ArrayList of all Hotel objects that can be created from "cells".
     */
    public ArrayList<Hotel> initialise(ArrayList<String[]> cells) {
        ArrayList<Hotel> result = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            String[] line = cells.get(i);
            // Once we reach a line where a new hotel is defined,
            // make a Hotel object and start adding room types.
            if (line.length == 12 && !line[HOTEL_INDEX].equals("")) {
                Hotel newHotel = new Hotel(line[HOTEL_INDEX]);
                TypeOfRoom newRoom = new TypeOfRoom(line[ROOM_INDEX], line[OCCUPANCY_MIN_INDEX],
                        line[OCCUPANCY_MAX_INDEX],
                        getRates(Arrays.copyOfRange(line, RATES_START_INDEX, RATES_END_INDEX + 1)));
                newHotel.addTypeOfRoom(newRoom);

                i++;

                // Keep adding rooms to the hotel until we reach another hotel
                while (cells.get(i)[HOTEL_INDEX].equals("")) {
                    line = cells.get(i);
                    newRoom = new TypeOfRoom(line[ROOM_INDEX], line[OCCUPANCY_MIN_INDEX],
                            line[OCCUPANCY_MAX_INDEX],
                            getRates(Arrays.copyOfRange(line, RATES_START_INDEX, RATES_END_INDEX + 1)));
                    newHotel.addTypeOfRoom(newRoom);
                    i++;

                    if (i == cells.size()) {
                        result.add(newHotel);
                        return result;
                    }
                }

                if (!cells.get(i)[HOTEL_INDEX].equals("")) {
                    i--;
                }
                result.add(newHotel);
            }
        }
        return result;
    }
}
