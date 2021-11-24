import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * This class calculates several metrics based off of the reservations and cancellations csv files
 * This class is abstract and all the methods are static since instances are not needed
 * to calculate the various metrics.
 *
 * @author Edison Cai 20241135
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
     * over a specific period of time.
     *
     * @param reservations        List of reservations
     * @param startDate           The start date of analysis
     * @param endDate             The end date of analysis
     * @param showUnoccupiedRooms Choose if you want to display rooms that haven't been booked
     * @return Get the occupancy rates of every hotel and room in the form of a list of Strings.
     */
    public static ArrayList<String> getOccupancyRatesAll(ArrayList<Reservation> reservations, String startDate, String endDate, boolean showUnoccupiedRooms) {
        ArrayList<String> result = new ArrayList<>();
        result.add(String.format("This is the occupancy rate for every hotel and room between %s and %s:", startDate, endDate));
        // For every hotel
        for (Hotel h : HotelInitialiser.allHotels) {
            int hotelOccupancy = 0;
            int hotelRoomsAmount = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotelInString(h.getTypeOfRooms());
            result.add("Hotel name: " + h.getHotelType());

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
                            hotelRooms.replace(res.getRooms().get(i).getRoomType(), hotelRooms.get(res.getRooms().get(i).getRoomType()) + res.getRooms().get(i).getRoomOccupancy());
                            hotelOccupancy += res.getRooms().get(i).getRoomOccupancy();
                            hotelRoomsAmount++;
                        }
                    }
                }

            }
            result.add("Hotel occupancy: " + hotelOccupancy);
            result.add("Amount of rooms booked: " + hotelRoomsAmount);

            for (Map.Entry<String, Integer> entry : hotelRooms.entrySet()) {
                // If you don't want to show empty rooms
                // Only show rooms that have some occupancy
                if (showUnoccupiedRooms) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room occupancy: " + entry.getValue());
                } else if (entry.getValue() != 0) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room occupancy: " + entry.getValue());
                }
            }
        }
        return result;
    }

    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time. By default, unoccupied rooms will also be
     * printed out.
     *
     * @param reservations List of reservations
     * @param startDate    The start date of analysis
     * @param endDate      The end date of analysis
     */
    public static ArrayList<String> getOccupancyRatesAll(ArrayList<Reservation> reservations, String startDate, String endDate) {
        return getOccupancyRatesAll(reservations, startDate, endDate, true);
    }

    /**
     * Returns the income figures for each hotel and room type
     * over a specific period of time.
     *
     * @param reservations        A list of all reservations
     * @param cancellations       A list of all cancellations
     * @param startDate           The start date of analysis
     * @param endDate             The end date of analysis
     * @param showUnoccupiedRooms Choose if you want to display rooms that haven't been booked
     * @return
     */
    public static ArrayList<String> calculateIncomeAll(ArrayList<Reservation> reservations, ArrayList<Cancellation> cancellations, String startDate, String endDate, boolean showUnoccupiedRooms) {
        // Initialising the list of Strings "result".
        ArrayList<String> result = new ArrayList<>();
        result.add(String.format("This is the income for every hotel and room between %s and %s:", startDate, endDate));

        // Initialising a HashMap of String/Double tuples. The String is the Hotel type
        // the double will be the income that a hotel has generated.
        HashMap<String, Double> hotelRooms = new HashMap<>();
        double totalIncome = 0;

        // For every hotel
        for (Hotel h : HotelInitialiser.allHotels) {
            int hotelIncome = 0;

            // Put all types of rooms in "hotelRooms".
            hotelRooms = getRoomsOfHotelInStringDouble(h.getTypeOfRooms());
            result.add("Hotel name: " + h.getHotelType());

            // Go through every reservation and if their dates are within startDate and
            // endDate, get their incomes and add to the income of the hotel.
            for (Reservation res : reservations) {
                if (compareDates(res.getCheckInDate().toString(), startDate) <= 0 && compareDates(res.getCheckInDate().toString(), endDate) >= 0) {
                    for (int i = 0; i < res.getNumberOfRooms() - 1; i++) {
                        if (hotelRooms.containsKey(res.getRooms().get(i).getRoomType())) {
                            // I must get the type of room from a string and its rates
                            TypeOfRoom room = findRoomType(res.getRooms().get(i).getRoomType(), HotelInitialiser.allHotels);
                            assert room != null;
                            double[] rates = room.getDailyRates();

                            double costOfRoom = getCostOfRoom(rates,
                                    res.getCheckInDate().toString(), res.getCheckOutDate().toString());

                            hotelRooms.replace(res.getRooms().get(i).getRoomType(), hotelRooms.get(res.getRooms().get(i).getRoomType()) + costOfRoom);
                            hotelIncome += costOfRoom;
                            totalIncome += costOfRoom;
                        }
                    }
                }
            }

            // Going through all cancellations to see if there are still some income to be made from reservations that can't be cancelled.
            for (Cancellation can : cancellations) {
                if (compareDates(can.getCancellationDate().toString(), startDate) <= 0 && compareDates(can.getCancellationDate().toString(), endDate) >= 0) {
                    for (int i = 0; i < can.getReservation().getNumberOfRooms() - 1; i++) {
                        if (hotelRooms.containsKey(can.getReservation().getRooms().get(i).getRoomType())) {
                            TypeOfRoom room = findRoomType(can.getReservation().getRooms().get(i).getRoomType(), HotelInitialiser.allHotels);
                            assert room != null;

                            hotelRooms.replace(can.getReservation().getRooms().get(i).getRoomType(), hotelRooms.get(can.getReservation().getRooms().get(i).getRoomType()) + can.getIncome());
                            hotelIncome += can.getIncome();
                            totalIncome += can.getIncome();
                        }
                    }
                }
            }

            result.add("Hotel income: " + hotelIncome);

            for (Map.Entry<String, Double> entry : hotelRooms.entrySet()) {
                // If you don't want to show empty rooms
                // Only show rooms that have some occupancy
                if (showUnoccupiedRooms) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room income: " + entry.getValue());
                } else if (entry.getValue() != 0) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room income: " + entry.getValue());
                }
            }


        }
        result.add("Total income: " + totalIncome);
        return result;
    }

    /**
     * Returns the income figures for each hotel and room type
     * over a specific period of time. By default, unoccupied rooms will also be
     * printed out.
     *
     * @param reservations  A list of all reservations
     * @param cancellations A list of all cancellations
     * @param startDate     The start date of analysis
     * @param endDate       The end date of analysis
     */
    public static ArrayList<String> calculateIncomeAll(ArrayList<Reservation> reservations, ArrayList<Cancellation> cancellations, String startDate, String endDate) {
        return calculateIncomeAll(reservations, cancellations, startDate, endDate, true);
    }

    /**
     * Helper function that places all TypeOfRoom objects in "rooms" into a HashMap with
     * TypeOfRoom-Integer pairs. This will be used in the occupancy calculations
     * to keep track of every room's occupancy.
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
     * Helper function that places all TypeOfRoom objects in "rooms" into a HashMap with
     * TypeOfRoom-Double pairs. This will be used in the income calculations
     * to keep track of every room's income.
     *
     * @param rooms A list of TypeOfRoom objects.
     * @return A HashMap mapping the names of each room in "rooms" to Doubles
     */
    private static HashMap<String, Double> getRoomsOfHotelInStringDouble(ArrayList<TypeOfRoom> rooms) {
        HashMap<String, Double> result = new HashMap<>();

        for (TypeOfRoom r : rooms) {
            result.put(r.getRoomType(), 0.0);
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
    private static int getCostOfRoom(double[] rates, String checkIn, String checkOut) {
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