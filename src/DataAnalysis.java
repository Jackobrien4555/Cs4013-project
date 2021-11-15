import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * This class calculates several metrics based off of the reservations and cancellations csv files
 * This class is abstract and all the methods are static since instances are not needed
 * to calculate the various metrics
 *
 * @author 20241135 Edison Cai
 * @since 27/10/2021
 */
public abstract class DataAnalysis {
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

    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time
     *
     * @param reservations
     * @param startDate           The start date of analysis
     * @param endDate             The end date of analysis
     * @param hotelList           A list of Hotel objects to iterate over
     * @param showUnoccupiedRooms Choose if you want to display rooms that haven't been booked
     */
    public static void getOccupancyRatesAll(ArrayList<Reservation> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList,
                                            boolean showUnoccupiedRooms) {
        System.out.printf("This is the occupancy rate for every hotel and room between %s and %s:\n", startDate, endDate);
        // For every hotel
        for (Hotel h : hotelList) {
            int hotelOccupancy = 0;
            int hotelRoomsAmount = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotelInString(h.getTypeOfRooms());
            System.out.println("Hotel name: " + h.getHotelType());

            // For every line in the reservations file
            for (Reservation res : reservations) {
                // Only process reservations that are within startDate and endDate
                if (compareDates(res.getCheckInDate().toString(), startDate) <= 0 && compareDates(res.getCheckInDate().toString(), endDate) >= 0) {
                    // Go through each room that's booked
                    for (int i = 0; i < res.getNumberOfRooms(); i++) {
                        // Check if a room name is a part of the hotel
                        // i will be the index of a room type. i + 1 will be the occupancy of that room
                        // If it's a recognised room, increment the occupancy of a room by the appropriate amount
                        // Finally, also add the occupancy to the hotel
                        if (hotelRooms.containsKey(res.getRooms().get(i).getRoomType())) {
                            hotelRooms.replace(res.getRooms().get(i).getRoomType(), hotelRooms.get(res.getRooms().get(i).getRoomType()) + Integer.parseInt(res.getRooms().get(i).getRoomOccupancy()));
                            hotelOccupancy += Integer.parseInt(res.getRooms().get(i).getRoomOccupancy());
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

    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time. By default, unoccupied rooms will also be
     * printed out.
     *
     * @param reservations
     * @param startDate    The start date of analysis
     * @param endDate      The end date of analysis
     * @param hotelList    A list of Hotel objects to iterate over
     */
    public static void getOccupancyRatesAll(ArrayList<Reservation> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        getOccupancyRatesAll(reservations, startDate, endDate, hotelList, true);
    }

    /**
     * Returns the income figures for each hotel and room type
     * over a specific period of time
     *
     * @param reservations
     * @param startDate           The start date of analysis
     * @param endDate             The end date of analysis
     * @param hotelList           A list of Hotel objects to iterate over
     * @param showUnoccupiedRooms Choose if you want to display rooms that haven't been booked
     */
    public static void calculateIncomeAll(ArrayList<Reservation> reservations, ArrayList<Cancellation> cancellations, String startDate, String endDate, ArrayList<Hotel> hotelList,
                                          boolean showUnoccupiedRooms) {
        System.out.printf("This is the income for every hotel and room between %s and %s:\n", startDate, endDate);
        // For every hotel
        for (Hotel h : hotelList) {
            int hotelIncome = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotelInString(h.getTypeOfRooms());
            System.out.println("Hotel name: " + h.getHotelType());
            for (Reservation res : reservations) {
                if (compareDates(res.getCheckInDate().toString(), startDate) <= 0 && compareDates(res.getCheckInDate().toString(), endDate) >= 0) {
                    for (int i = 0; i < res.getNumberOfRooms() - 1; i++) {
                        if (hotelRooms.containsKey(res.getRooms().get(i).getRoomType())) {
                            // I must get the type of room from a string and its rates
                            TypeOfRoom room = findRoomType(res.getRooms().get(i).getRoomType(), hotelList);
                            assert room != null;
                            int[] rates = room.getDailyRates();

                            int costOfRoom = getCostOfRoom(rates,
                                    res.getCheckInDate().toString(), res.getCheckOutDate().toString());

                            hotelRooms.replace(res.getRooms().get(i).getRoomType(), hotelRooms.get(res.getRooms().get(i).getRoomType()) + costOfRoom);
                            hotelIncome += costOfRoom;
                        }
                    }
                }
            }

            for(Cancellation can : cancellations){
                if (compareDates(can.getReservation().getCheckInDate().toString(), startDate) <= 0 && compareDates(can.getReservation().getCheckInDate().toString(), endDate) >= 0) {
                    for (int i = 0; i < can.getReservation().getNumberOfRooms() - 1; i++) {
                        if (hotelRooms.containsKey(can.getReservation().getRooms().get(i).getRoomType())) {
                            TypeOfRoom room = findRoomType(can.getReservation().getRooms().get(i).getRoomType(), hotelList);
                            assert room != null;

                            hotelRooms.replace(can.getReservation().getRooms().get(i).getRoomType(), hotelRooms.get(can.getReservation().getRooms().get(i).getRoomType()) + can.getIncome());
                            hotelIncome += can.getIncome();
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
     * Returns the income figures for each hotel and room type
     * over a specific period of time. By default, unoccupied rooms will also be
     * printed out.
     *
     * @param reservations
     * @param startDate    The start date of analysis
     * @param endDate      The end date of analysis
     * @param hotelList    A list of Hotel objects to iterate over
     */
    public static void calculateIncomeAll(ArrayList<Reservation> reservations, ArrayList<Cancellation> cancellations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        calculateIncomeAll(reservations, cancellations, startDate, endDate, hotelList, true);
    }


    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time
     *
     * @param reservations
     * @param startDate           The start date of analysis
     * @param endDate             The end date of analysis
     * @param hotelList           A list of Hotel objects to iterate over
     * @param showUnoccupiedRooms Choose if you want to display rooms that haven't been booked
     */
    public static void getOccupancyRatesAllString(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList,
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

    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time. By default, unoccupied rooms will also be
     * printed out.
     *
     * @param reservations
     * @param startDate    The start date of analysis
     * @param endDate      The end date of analysis
     * @param hotelList    A list of Hotel objects to iterate over
     */
    public static void getOccupancyRatesAllString(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        getOccupancyRatesAllString(reservations, startDate, endDate, hotelList, true);
    }


    /**
     * Returns the income figures for each hotel and room type
     * over a specific period of time
     *
     * @param reservations
     * @param startDate           The start date of analysis
     * @param endDate             The end date of analysis
     * @param hotelList           A list of Hotel objects to iterate over
     * @param showUnoccupiedRooms Choose if you want to display rooms that haven't been booked
     */
    public static void calculateIncomeAllString(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList,
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
     * Returns the income figures for each hotel and room type
     * over a specific period of time. By default, unoccupied rooms will also be
     * printed out.
     *
     * @param reservations
     * @param startDate    The start date of analysis
     * @param endDate      The end date of analysis
     * @param hotelList    A list of Hotel objects to iterate over
     */
    public static void calculateIncomeAllString(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList) {
        calculateIncomeAllString(reservations, startDate, endDate, hotelList, true);
    }

    /**
     * Helper function that places all TypeOfRoom objects in "rooms" into a HashMap with
     * TypeOfRoom-Integer pairs. This will be used in the occupancy and income calculations
     * to keep track of every room's occupancy/income.
     *
     * @param rooms A list of TypeOfRoom objects.
     * @return A HashMap mapping the names of each room in "rooms" to Integers
     */
    private static HashMap<String, Integer> getRoomsOfHotelInString(ArrayList<TypeOfRoom> rooms) {
        HashMap<String, Integer> result = new HashMap<>();

        for (TypeOfRoom r : rooms) {
            result.put(r.getRoomType(), 0);
        }

        return result;
    }


    /**
     * Helper method for comparing 2 dates in String form
     * formatted as YYYY-MM-DD.
     *
     * @param date1 First date.
     * @param date2 Second date.
     * @return 0 if the dates are the same, <0 if date1 is later, >0 if date2 is later
     */
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
     * the check-in date and the check-out date.
     *
     * @param rates    A room's rates for each day of the week.
     * @param checkIn  The check-in date.
     * @param checkOut The check-out date.
     * @return The total cost to book a room.
     */
    private static int getCostOfRoom(int[] rates, String checkIn, String checkOut) {
        String[] dateValuesIn = checkIn.split("-");
        String[] dateValuesOut = checkOut.split("-");

        LocalDate checkInDate = LocalDate.of(Integer.parseInt(dateValuesIn[0]), Integer.parseInt(dateValuesIn[1]), Integer.parseInt(dateValuesIn[2]));
        LocalDate checkOutDate = LocalDate.of(Integer.parseInt(dateValuesOut[0]), Integer.parseInt(dateValuesOut[1]), Integer.parseInt(dateValuesOut[2]));

        int result = 0;

        // Iterate through the dates
        while (checkInDate.compareTo(checkOutDate) < 0) {
            // checkInDate.getDayOfWeek().getValue() returns an int
            // 1 is Monday, 7 is Sunday
            int value = checkInDate.getDayOfWeek().getValue();
            //result += rates[2 + checkInDate.getDayOfWeek().getValue()];
            result += rates[checkInDate.getDayOfWeek().getValue() - 1];
            checkInDate = checkInDate.plusDays(1);
        }

        return result;
    }

    /**
     * Iterates through every room of every hotel to see if any of their names
     * match the one passed in through roomName and returns its corresponding TypeOfRoom object
     *
     * @param roomName  The name of a room
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