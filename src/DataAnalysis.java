import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class calculates several metrics based off of the reservations and cancellations csv files
 * This class is abstract and all the methods are static since instances are not needed
 * to calculate the various metrics.
 *
 * @author Edison Cai 20241135
 * @since 27/10/2021
 */
public abstract class DataAnalysis {

    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time.
     *
     * @param reservations        List of reservations
     * @param startDate           The start date of analysis
     * @param endDate             The end date of analysis
     * @param showUnoccupiedRooms Choose if you want to display rooms that haven't been booked
     * @return The occupancy rates of every hotel and room in the form of a list of Strings.
     */
    public static ArrayList<String> getOccupancyRatesAll(ArrayList<Reservation> reservations, LocalDate startDate, LocalDate endDate, boolean showUnoccupiedRooms) {
        ArrayList<String> result = new ArrayList<>();
        result.add(String.format("This is the occupancy rate for every hotel and room between %s and %s:", startDate, endDate));
        result.add("---------------------------------------");
        // For every hotel
        for (Hotel h : HotelInitialiser.getAllHotels()) {
            // Initialise the occupancy and the amount of rooms booked for a hotel.
            int hotelOccupancy = 0;
            int hotelRoomsAmount = 0;

            // Initialising a HashMap of TypeOfRoom/Integer pairs. This is to keep track of the occupancy for every type of room.
            HashMap<String, Integer> hotelRoomsOccupancy = getRoomsOfHotelInStringInt(h.getTypeOfRooms());

            // Initialising a HashMap of TypeOfRoom/Integer pairs. This is to keep track of the rooms booked for every type of room.
            HashMap<String, Integer> hotelRoomsBooked = getRoomsOfHotelInStringInt(h.getTypeOfRooms());

            result.add("Hotel name: " + h.getHotelType());

            // For every reservation...
            for (Reservation res : reservations) {
                // Only process reservations whose check-in dates are within startDate and endDate.
                if (res.getCheckInDate().compareTo(startDate) >= 0 && res.getCheckInDate().compareTo(endDate) <= 0) {
                    // Go through each room that's booked.
                    for (int i = 0; i < res.getNumberOfRooms(); i++) {
                        // Check if a room name is a part of the hotel
                        // If it's a recognised room, increment the total occupancy of a room by the occupancy of the room in the reservation.
                        // Finally, also add the occupancy to the hotel.
                        // Increment the amount of rooms booked by +1.
                        if (hotelRoomsOccupancy.containsKey(res.getRooms().get(i).getRoomType())) {
                            hotelRoomsOccupancy.replace(res.getRooms().get(i).getRoomType(), hotelRoomsOccupancy.get(res.getRooms().get(i).getRoomType()) + res.getRooms().get(i).getRoomOccupancy());
                            hotelOccupancy += res.getRooms().get(i).getRoomOccupancy();
                            hotelRoomsBooked.replace(res.getRooms().get(i).getRoomType(), hotelRoomsBooked.get(res.getRooms().get(i).getRoomType()) + 1);
                            hotelRoomsAmount++;
                        }
                    }
                }

            }

            result.add("Hotel occupancy: " + hotelOccupancy);
            result.add("Amount of rooms booked: " + hotelRoomsAmount);
            result.add("\n");

            // Go through every room/occupancy pair in hotelRoomsOccupancy and print out the name, occupancy and the amount booked in the date range.
            for (Map.Entry<String, Integer> entry : hotelRoomsOccupancy.entrySet()) {
                // If you don't want to show empty rooms,
                // Only show rooms that have some occupancy.
                if (showUnoccupiedRooms) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room occupancy: " + entry.getValue());
                    result.add("Room booked: " + hotelRoomsBooked.get(entry.getKey()));
                    result.add("\n");
                } else if (entry.getValue() != 0) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room occupancy: " + entry.getValue());
                    result.add("Room booked: " + hotelRoomsBooked.get(entry.getKey()));
                    result.add("\n");
                }
            }

            result.add("---------------------------------------");
        }
        return result;
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
     * @return The income of every hotel and room in the form of a list of Strings.
     */
    public static ArrayList<String> calculateIncomeAll(ArrayList<Reservation> reservations, ArrayList<Cancellation> cancellations, LocalDate startDate, LocalDate endDate, boolean showUnoccupiedRooms) {
        // Initialising the list of Strings "result".
        ArrayList<String> result = new ArrayList<>();
        result.add(String.format("This is the income for every hotel and room between %s and %s:", startDate, endDate));
        result.add("---------------------------------------");

        // Initialising a HashMap of String/Double tuples. The String is the Hotel type and
        // the double will be the income that a hotel has generated.
        HashMap<String, Double> hotelRooms;
        double totalIncome = 0;
        double cancellationIncome = 0;
        double cancellationLoss = 0;

        // For every hotel
        for (Hotel h : HotelInitialiser.getAllHotels()) {
            int hotelIncome = 0;

            // Put all types of rooms of a hotel in "hotelRooms". All their incomes will be set to 0.
            hotelRooms = getRoomsOfHotelInStringDouble(h.getTypeOfRooms());
            result.add("Hotel name: " + h.getHotelType());

            // Go through every reservation and if their dates are within startDate and
            // endDate, get their incomes and add to the income of the hotel.
            for (Reservation res : reservations) {
                if (res.getCheckInDate().compareTo(startDate) >= 0 && res.getCheckInDate().compareTo(endDate) <= 0) {
                    // Go through every room of a reservation, find out what type of room they are.
                    for (int i = 0; i < res.getNumberOfRooms(); i++) {
                        if (hotelRooms.containsKey(res.getRooms().get(i).getRoomType())) {
                            double costOfRoom;
                            // Getting the TypeOfRoom with all of its rates from the findRoomType method.
                            TypeOfRoom room = findRoomType(res.getRooms().get(i).getRoomType(), HotelInitialiser.getAllHotels());

                            // Getting daily rates of a room.
                            double[] rates = room.getDailyRates();

                            // Get the cost of a room from startDate to endDate.
                            // If the check-out date of the reservation is later than endDate,
                            // only find the income up to endDate. Otherwise, calculate up to
                            // the check-out date.

                            //System.out.println(res.getCheckInDate());
                            if (res.getCheckOutDate().compareTo(endDate) < 0) {
                                costOfRoom = getCostOfRoom(rates, res.getCheckInDate(), res.getCheckOutDate());
                            } else {
                                costOfRoom = getCostOfRoom(rates, res.getCheckInDate(), endDate);
                            }

                            // Add to the income of a type of room, as well as its corresponding hotel and also to the total income.
                            hotelRooms.replace(res.getRooms().get(i).getRoomType(), hotelRooms.get(res.getRooms().get(i).getRoomType()) + costOfRoom);
                            hotelIncome += costOfRoom;
                            totalIncome += costOfRoom;
                        }
                    }
                }
            }


            result.add("Hotel income: " + hotelIncome);
            result.add("\n");

            for (Map.Entry<String, Double> entry : hotelRooms.entrySet()) {
                // If you don't want to show empty rooms
                // Only show rooms that have some occupancy
                if (showUnoccupiedRooms) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room income: " + entry.getValue());
                    result.add("\n");
                } else if (entry.getValue() != 0) {
                    result.add("Room name: " + entry.getKey());
                    result.add("Room income: " + entry.getValue());
                    result.add("\n");
                }
            }
            result.add("---------------------------------------");

        }

        // Going through all cancellations to see if there are still some income to be made from reservations that can't be cancelled.
        for (Cancellation can : cancellations) {
            if (can.getCancellationDate().compareTo(startDate) >= 0 && can.getCancellationDate().compareTo(endDate) <= 0) {
//                    for (int i = 0; i < can.getReservation().getNumberOfRooms() - 1; i++) {
//                        if (hotelRooms.containsKey(can.getReservation().getRooms().get(i).getRoomType())) {
//                            TypeOfRoom room = findRoomType(can.getReservation().getRooms().get(i).getRoomType(), HotelInitialiser.allHotels);
//                            assert room != null;
//
//                            hotelRooms.replace(can.getReservation().getRooms().get(i).getRoomType(), hotelRooms.get(can.getReservation().getRooms().get(i).getRoomType()) + can.getIncome());
//                            hotelIncome += can.getIncome();
//                            totalIncome += can.getIncome();
//                        }
//                    }
                // If the income of a Cancellation is 0, we can assume that it has been refunded. Add the would-be income of the reservation
                // into cancellationLoss. Otherwise, add it to cancellationIncome and totalIncome.
                if (can.getIncome() == 0) {
                    cancellationLoss += can.getReservation().getTotalCost();
                } else {
                    cancellationIncome += can.getIncome();
                    totalIncome += can.getIncome();
                }
            }
        }

        result.add("Income lost from cancellations: " + cancellationLoss);
        result.add("Income retained from cancellations: " + cancellationIncome);
        result.add("Income from all successful reservations: " + (totalIncome - cancellationIncome));
        result.add("Total income: " + totalIncome);

        return result;
    }

    /*
     * Helper function that places all TypeOfRoom objects in "rooms" into a HashMap with
     * TypeOfRoom-Integer(0) pairs. This will be used in the occupancy calculations
     * to keep track of every room's occupancy.
     */
    private static HashMap<String, Integer> getRoomsOfHotelInStringInt(ArrayList<TypeOfRoom> rooms) {
        HashMap<String, Integer> result = new HashMap<>();

        for (TypeOfRoom r : rooms) {
            result.put(r.getRoomType(), 0);
        }

        return result;
    }

    /*
     * Helper function that places all TypeOfRoom objects in "rooms" into a HashMap with
     * TypeOfRoom-Double(0.0) pairs. This will be used in the income calculations
     * to keep track of every room's income.
     */
    private static HashMap<String, Double> getRoomsOfHotelInStringDouble(ArrayList<TypeOfRoom> rooms) {
        HashMap<String, Double> result = new HashMap<>();

        for (TypeOfRoom r : rooms) {
            result.put(r.getRoomType(), 0.0);
        }

        return result;
    }

    /*
     * Returns the cost of a room by passing in the room's rates for the week,
     * the check-in date and the check-out date.
     */
    private static int getCostOfRoom(double[] rates, LocalDate checkIn, LocalDate checkOut) {
        int result = 0;

        // Iterate through the dates
        while (checkIn.compareTo(checkOut) < 0) {
            // checkInDate.getDayOfWeek().getValue() returns an int
            // 1 is Monday, 7 is Sunday. To get the corresponding rate, subtract 1.
            result += rates[checkIn.getDayOfWeek().getValue() - 1];
            checkIn = checkIn.plusDays(1);
        }

        return result;
    }

    /*
     * Iterates through every room of every hotel to see if any of their names
     * match the one passed in through roomName and returns its corresponding TypeOfRoom object
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

    /**
     * Print all analytics information.
     * @param analytics List of Strings that contain various information on
     *                  analytics.
     */
    public static void printAnalytics(ArrayList<String> analytics) {
        for(String s : analytics){
            System.out.println(s);
        }
    }
}