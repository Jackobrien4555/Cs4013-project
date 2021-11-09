import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * This class calculates several metrics based off of the reservations
 *
 * @author 20241135
 */
public class DataAnalysis {
    private static final int RES_NUMBER_INDEX = 0;
    private static final int RES_NAME_INDEX = 1;
    private static final int RES_TYPE_INDEX = 2;
    private static final int RES_START_INDEX = 3;
    private static final int RES_END_INDEX = 4;
    private static final int ROOM_NUMBER_INDEX = 5;
    private static final int FIRST_ROOM_INDEX = 6;

    public DataAnalysis() {

    }

    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time.
     *
     * @param startDate The starting date
     * @param endDate   The end date
     */
    public static void getOccupancyRatesAll(String startDate, String endDate, ArrayList<Hotel> hotels) {
        System.out.printf("This is the occupancy rate for every hotel and room between %s and %s\n:", startDate, endDate);
        for (Hotel hotel : hotels) {
            getOccupancyRatesHotel(startDate, endDate, hotel);
        }
    }

    public static void getOccupancyRatesAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        getOccupancyRatesAll(reservations, startDate, endDate, hotelList, true);
    }

    public static void getOccupancyRatesAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList,
                                            boolean showUnoccupiedRooms) {
        System.out.printf("This is the occupancy rate for every hotel and room between %s and %s:\n", startDate, endDate);
        // For every hotel
        for (Hotel h : hotelList) {
            int hotelOccupancy = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotel(h.getTypeOfRoom());
            System.out.println("Hotel name: " + h.getHotelType());
            for (String[] cells : reservations) {

                if (compareDates(cells[RES_START_INDEX], startDate) <= 0 && compareDates(cells[RES_START_INDEX], endDate) >= 0) {
                    for (int i = FIRST_ROOM_INDEX; i < cells.length; i++) {
                        if (hotelRooms.containsKey(cells[i])) {
                            hotelRooms.replace(cells[i], hotelRooms.get(cells[i]) + Integer.parseInt(cells[i + 1]));
                            hotelOccupancy += Integer.parseInt(cells[i + 1]);
                        }
                    }
                }

            }
            System.out.println("Hotel occupancy: " + hotelOccupancy);

            for (Map.Entry<String, Integer> entry : hotelRooms.entrySet()) {
                // If you don't want to show empty rooms
                // Only show rooms that have some occupancy
                if (showUnoccupiedRooms) {
                    System.out.println("Room name: " + entry.getKey());
                    System.out.println("Room occupancy: " + entry.getValue());
                } else if (entry.getValue() != 0) {
                    System.out.println("Room name: " + entry.getKey());
                    System.out.println("Room occupancy: " + entry.getValue());
                }
            }
            System.out.println();
        }
    }

    private static void getOccupancyRatesHotel(String startDate, String endDate, Hotel hotel) {
        System.out.printf("Occupancy summary for: %s\n", hotel.getHotelType());

    }

    private static void getOccupancyRatesRoom(String startDate, String endDate) {

    }

    /**
     * Returns the income for each hotel and room type
     * over a specific period of time.
     */
    public static void calculateIncomeAll(String startDate, String endDate, ArrayList<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            calculateIncomeHotel(startDate, endDate, hotel);
        }
    }

    public static void calculateIncomeAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        calculateIncomeAll(reservations, startDate, endDate, hotelList, true);
    }

    public static void calculateIncomeAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList,
                                          boolean showUnoccupiedRooms) {
        System.out.printf("This is the income for every hotel and room between %s and %s:\n", startDate, endDate);
        // For every hotel
        for (Hotel h : hotelList) {
            int hotelIncome = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotel(h.getTypeOfRoom());
            System.out.println("Hotel name: " + h.getHotelType());
            for (String[] cells : reservations) {

                if (compareDates(cells[RES_START_INDEX], startDate) <= 0 && compareDates(cells[RES_START_INDEX], endDate) >= 0) {
                    for (int i = FIRST_ROOM_INDEX; i < cells.length; i++) {
                        if (hotelRooms.containsKey(cells[i])) {
                            hotelIncome += Integer.parseInt(cells[cells.length - 1]);
                        }
                    }
                }

            }
            System.out.println("Hotel occupancy: " + hotelIncome);

            for (Map.Entry<String, Integer> entry : hotelRooms.entrySet()) {
                // If you don't want to show empty rooms
                // Only show rooms that have some occupancy
                if (showUnoccupiedRooms) {
                    System.out.println("Room name: " + entry.getKey());
                    System.out.println("Room income: " + entry.getValue());
                } else if (entry.getValue() != 0) {
                    System.out.println("Room name: " + entry.getKey());
                    System.out.println("Room income: " + entry.getValue());
                }
            }
            System.out.println();
        }

    }


    private static void calculateIncomeHotel(String startDate, String endDate, Hotel hotel) {

    }

    private static void calculateIncomeRoom() {

    }

    private static HashMap<String, Integer> getRoomsOfHotel(ArrayList<TypeOfRoom> rooms) {
        HashMap<String, Integer> result = new HashMap<>();

        for (TypeOfRoom r : rooms) {
            result.put(r.getRoomType(), 0);
        }

        return result;
    }

    private static int compareDates(String date1, String date2) {
        StringTokenizer tk1 = new StringTokenizer(date1, "-");
        StringTokenizer tk2 = new StringTokenizer(date2, "-");

        int year1 = Integer.parseInt(tk1.nextToken());
        int year2 = Integer.parseInt(tk2.nextToken());
        if (year1 != year2) {
            return year2 - year1;
        }


        int month1 = Integer.parseInt(tk1.nextToken());
        int month2 = Integer.parseInt(tk2.nextToken());
        if (month1 != month2) {
            return month2 - month1;
        }

        int day1 = Integer.parseInt(tk1.nextToken());
        int day2 = Integer.parseInt(tk2.nextToken());
        if (day1 != day2) {
            return day2 - day1;
        }

        return 0;
    }
}