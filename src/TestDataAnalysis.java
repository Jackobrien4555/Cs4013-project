/**
 * For testing the DataAnalysis class
 *
 * @author 20241135
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestDataAnalysis {
    /*
   All of these constant ints refer to the index of different values in a reservation
   line
    */
    private static final int RES_NUMBER_INDEX = 0;
    private static final int RES_NAME_INDEX = 1;
    private static final int RES_TYPE_INDEX = 2;
    private static final int RES_START_INDEX = 3;
    private static final int RES_END_INDEX = 4;
    private static final int ROOM_NUMBER_INDEX = 5;
    private static final int FIRST_ROOM_INDEX = 6;

    public static void main(String[] args) {
        String fileRes = "reservations/random_res.csv";
        String fileCan = "reservations/cancellations.csv";
        String hotelsFile = "l4Hotels.csv";

        String start_date = "2019-09-03";
        String end_date = "2024-09-03";

        ArrayList<String[]> reservations = new ArrayList<>();
        HotelInitialiser.allHotels = HotelInitialiser.initialise(HotelInitialiser.getFileCells(hotelsFile));

        ArrayList<Reservation> reservationsAll = Reader.readReservations(fileRes);
        ArrayList<Cancellation> cancellationsAll = Reader.readCancellations(fileCan);
        print(DataAnalysis.getOccupancyRatesAll(reservationsAll, LocalDate.parse(start_date), LocalDate.parse(end_date), false));
        System.out.println();
        print(DataAnalysis.calculateIncomeAll(reservationsAll, cancellationsAll, LocalDate.parse(start_date), LocalDate.parse(end_date), false));
    }

    public static void print(ArrayList<String> lines){
        for(String s : lines){
            System.out.println(s);
        }
    }
}
