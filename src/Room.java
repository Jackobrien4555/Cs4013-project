import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * class Room defines a room
 *
 * @author Jack O Brien
 * @since 05/11/2021
 */
public class Room {
    private String typeOfRoom;
    private int amountOfOccupants;

    /**
     * @param type      the type of room
     * @param occupancy the amount of occupants able to stay in the room
     */
    public Room(String type, int occupancy) {
        this.typeOfRoom = type;
        this.amountOfOccupants = occupancy;
    }

    /**
     * returns type of room
     *
     * @return typeOfRoom the type of room
     */
    public String getRoomType() {
        return typeOfRoom;
    }

    /**
     * gets occupancy of the room
     *
     * @return amountOfOccupants gets amount able to stay in room
     */
    public int getRoomOccupancy() {
        return amountOfOccupants;
    }

    /**
     * sets the type of room
     *
     * @param type the type of room
     */
    public void setRoomType(String type) {
        typeOfRoom = type;
    }

    /**
     * sets the amount able to stay in room
     *
     * @param amount the amount able to stay in room
     */
    public void setRoomOccupancy(int amount) {
        amountOfOccupants = amount;
    }

    /**
     * @param room
     * @param checkIn
     * @param checkOut
     * @return
     * @author Jack O Brien
     */
    public static boolean roomIsAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        int amountBooked = 0;
        int maxCapacity = room.findRoomType().getNumAvailable();
        boolean result = true;
        for (Reservation res : ReservationCancellationManager.getAllReservations()) {
            if (res.getCheckInDate().compareTo(checkIn) >= 0 && res.getCheckInDate().compareTo(checkOut) < 0) {
                for (int i = 0; i < res.getNumberOfRooms(); i++) {
                    if (room.equals(res.getRooms().get(i))) {
                        amountBooked++;

                        if (amountBooked >= maxCapacity) {
                            result = false;
                        }
                    }
                }
            }
        }
        System.out.println("The amount of this room available far between " + checkIn.toString() + " and " + checkOut.toString() + ": " + (maxCapacity - amountBooked));
        return result;
    }

    /**
     * Returns the cost of a room by passing in the room's rates for the week,
     * the check-in date and the check-out date.
     *
     * @param rates    A room's rates for each day of the week.
     * @param checkIn  The check-in date.
     * @param checkOut The check-out date.
     * @return The total cost to book a room.
     * @author Jack O Brien
     */
    public static int getCostOfRoom(double[] rates, LocalDate checkIn, LocalDate checkOut) {
        int result = 0;

        // Iterate through the dates
        while (checkIn.compareTo(checkOut) < 0) {
            // checkInDate.getDayOfWeek().getValue() returns an int
            // 1 is Monday, 7 is Sunday. To get the corresponding rate, subtract 1.
            int value = checkIn.getDayOfWeek().getValue();
            result += rates[checkIn.getDayOfWeek().getValue() - 1];
            checkIn = checkIn.plusDays(1);
        }

        return result;
    }

    public TypeOfRoom findRoomType(){
        for(Hotel h : HotelInitialiser.getAllHotels()){
            for(TypeOfRoom t : h.getTypeOfRooms()){
                if(t.getRoomType().equals(typeOfRoom)){
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return typeOfRoom.equals(room.typeOfRoom);
    }
}
