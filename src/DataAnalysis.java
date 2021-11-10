import java.time.LocalDate;
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
    public static void getOccupancyRatesAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        getOccupancyRatesAll(reservations, startDate, endDate, hotelList, true);
    }

    public static void getOccupancyRatesAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList,
                                            boolean showUnoccupiedRooms) {
        System.out.printf("This is the occupancy rate for every hotel and room between %s and %s:\n", startDate, endDate);
        // For every hotel
        for (Hotel h : hotelList) {
            int hotelOccupancy = 0;
            int hotelRoomsAmount = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotelInString(h.getTypeOfRooms());
            System.out.println("Hotel name: " + h.getHotelType());

            // For every line in the reservations file
            for (String[] cells : reservations) {
                // Only process reservations that are within startDate and endDate
                if (compareDates(cells[RES_START_INDEX], startDate) <= 0 && compareDates(cells[RES_START_INDEX], endDate) >= 0) {
                    // Go through each room that's booked
                    for (int i = FIRST_ROOM_INDEX; i < cells.length; i++) {
                        // Check if a room name is a part of the hotel
                        // i will be the index of a room type. i + 1 will be the occupancy of that room
                        // If it's a recognised room, increment the occupancy of a room by the appropriate amount
                        // Finally, also add the occupancy to the hotel
                        if (hotelRooms.containsKey(cells[i])) {
                            hotelRooms.replace(cells[i], hotelRooms.get(cells[i]) + Integer.parseInt(cells[i + 1]));
                            hotelOccupancy += Integer.parseInt(cells[i + 1]);
                            hotelRoomsAmount++;
                        }
                    }
                }

            }
            System.out.println("Hotel occupancy: " + hotelOccupancy);
            System.out.println("Amount of rooms booked: " + hotelRoomsAmount);

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

    public static void calculateIncomeAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        calculateIncomeAll(reservations, startDate, endDate, hotelList, true);
    }

    public static void calculateIncomeAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList,
                                          boolean showUnoccupiedRooms) {
        System.out.printf("This is the income for every hotel and room between %s and %s:\n", startDate, endDate);
        // For every hotel
        for (Hotel h : hotelList) {
            int hotelIncome = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotelInString(h.getTypeOfRooms());
            System.out.println("Hotel name: " + h.getHotelType());
            for (String[] cells : reservations) {

                if (compareDates(cells[RES_START_INDEX], startDate) <= 0 && compareDates(cells[RES_START_INDEX], endDate) >= 0) {
                    for (int i = FIRST_ROOM_INDEX; i < cells.length - 1; i++) {
                        if (hotelRooms.containsKey(cells[i])) {
                            // I must get the type of room from a string and its rates
                            TypeOfRoom room = findRoomType(cells[i], hotelList);
                            assert room != null;
                            int[] rates = room.getDailyRates();

                            int costOfRoom = getCostOfRoom(rates,
                                    cells[RES_START_INDEX], cells[RES_END_INDEX]);

                            hotelRooms.replace(cells[i], hotelRooms.get(cells[i]) + costOfRoom);
                            hotelIncome += costOfRoom;
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

    /**
     * @param rooms
     * @return A HashMap mapping TypeOfRooms to Integers.
     */
    private static HashMap<TypeOfRoom, Integer> getRoomsOfHotel(ArrayList<TypeOfRoom> rooms) {
        HashMap<TypeOfRoom, Integer> result = new HashMap<>();

        for (TypeOfRoom r : rooms) {
            result.put(r, 0);
        }

        return result;
    }

    /**
     * @param rooms
     * @return A HashMap mapping the names of each room in "rooms" to Integers
     */
    private static HashMap<String, Integer> getRoomsOfHotelInString(ArrayList<TypeOfRoom> rooms) {
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

    /**
     * Returns the cost of a room by passing in the room's rates for the week,
     * the check-in date and the check-out date
     * @param rates
     * @param checkIn
     * @param checkOut
     * @return
     */
    private static int getCostOfRoom(int[] rates, String checkIn, String checkOut) {
        // To get the cost, I need to get the days of the different dates
        // I need to get the costs of every room then add them at the end
        String[] dateValuesIn = checkIn.split("-");
        String[] dateValuesOut = checkOut.split("-");

        LocalDate checkInDate = LocalDate.of(Integer.parseInt(dateValuesIn[0]), Integer.parseInt(dateValuesIn[1]), Integer.parseInt(dateValuesIn[2]));
        LocalDate checkOutDate = LocalDate.of(Integer.parseInt(dateValuesOut[0]), Integer.parseInt(dateValuesOut[1]), Integer.parseInt(dateValuesOut[2]));

        int result = 0;

        // Iterate through the dates
        while (checkInDate.compareTo(checkOutDate) < 0) {
            // checkInDate.getDayOfWeek().getValue() returns an int
            // 1 is Monday, 7 is Sunday
            result += rates[2 + checkInDate.getDayOfWeek().getValue()];
            checkInDate = checkInDate.plusDays(1);
        }

        return result;
    }

    /**
     * Iterates through every room of every hotel to see if any of their names
     * match the one passed in through roomName and returns its corresponding TypeOfRoom object
     * @param roomName The name of a room
     * @param allHotels An ArrayList of all hotels so that this method can iterate
     *                  through every room
     * @return The TypeOfRoom object that was represented by roomName
     */
    private static TypeOfRoom findRoomType(String roomName, ArrayList<Hotel> allHotels) {
        for (Hotel h : allHotels) {
            for (TypeOfRoom r : h.getTypeOfRooms()) {
                if (r.getRoomType().equals(roomName)) {
                    return r;
                }
            }
        }
        return null;
    }
}