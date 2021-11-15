import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestDataAnalysis {
    //TODO Format date strings so that they can be easily converted to LocalDate
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
        String filename = "reservations/random_res.csv";
        String hotelsFile = "l4Hotels.csv";

        String start_date = "2020-9-3";
        String end_date = "2021-9-3";

        ArrayList<String[]> reservations = new ArrayList<>();
        HotelInitialiser initialiser = new HotelInitialiser();
        ArrayList<Hotel> initialisedHotels = initialiser.initialise(HotelInitialiser.getFileCells(hotelsFile));

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

//        DataAnalysis.getOccupancyRatesAllString(reservations, start_date, end_date,
//                initialisedHotels, false);
//
//        DataAnalysis.getOccupancyRatesAllString(reservations, start_date, end_date,
//                initialisedHotels);
//
//        DataAnalysis.calculateIncomeAllString(reservations, start_date, end_date, initialisedHotels, false);

        //initialiseReservations(reservations);
    }

    public static ArrayList<Reservation> initialiseReservations(ArrayList<String[]> reservations) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for (String[] line : reservations) {
            ArrayList<Room> rooms = new ArrayList<>();
            int i = FIRST_ROOM_INDEX;
            while(i < line.length - 1){
                String roomName = line[i];
                String occupants = line[i + 1];
                rooms.add(new Room(roomName, occupants, 0));
                i += 2;
            }
            LocalDate date = LocalDate.now();
            // To be updated
            Reservation res = new Reservation(Integer.parseInt(line[RES_NUMBER_INDEX]), line[RES_NAME_INDEX], line[RES_TYPE_INDEX],
                    date, date, rooms.size(), rooms);
            res.setTotalCost(Integer.parseInt(line[line.length - 1]));
            System.out.println(res);
            reservationList.add(res);
        }
        return reservationList;
    }
}
