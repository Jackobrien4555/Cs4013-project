import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Reservation class defines and stores a reservation
 * @author David Walsh 20276885
 */

public class Reservation {
    private final int resNumber;
    private final String resName;
    private final String resType;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int numberOfRooms;
    private ArrayList<Room> rooms = new ArrayList<>();
    private double totalCost;

    /**
     * Constructor to create new instance of Reservation (used when reading from user input).
     * @param resNumber     An integer that identifies the reservation.
     * @param resName       The chosen name of the reservation.
     * @param resType       The type of the reservation (S or AP).
     * @param checkInDate   The date that the customer is planned to check in at.
     * @param checkOutDate  The date that the customer is planned to check out at.
     * @param numberOfRooms The number of rooms reserved.
     */
    public Reservation(int resNumber, String resName, String resType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfRooms) {
        this.resNumber = resNumber;
        this.resName = resName;
        this.resType = resType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Constructor to create new instance of Reservation (used when reading from user input).
     * @param resNumber     An integer that identifies the reservation.
     * @param resName       The chosen name of the reservation.
     * @param resType       The type of the reservation (S or AP).
     * @param checkInDate   The date that the customer is planned to check in at.
     * @param checkOutDate  The date that the customer is planned to check out at.
     * @param numberOfRooms The number of rooms reserved.
     * @param rooms         The rooms reserved by the reservation
     * @param totalCost     The total cost of the reservation.
     */
    public Reservation(int resNumber, String resName, String resType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfRooms, ArrayList<Room> rooms, double totalCost) {
        this.resNumber = resNumber;
        this.resName = resName;
        this.resType = resType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfRooms = numberOfRooms;
        this.rooms = rooms;
        this.totalCost = totalCost;
    }

    /**
     * This method returns the reservation number.
     * @return resNumber the number that identifies the reservation.
     */
    public int getResNumber() {
        return resNumber;
    }

    /**
     * This method returns the reservation name.
     * @return resName the name of the reservation.
     */
    public String getResName() {
        return resName;
    }

    /**
     * This method returns the reservation type, Standard (S) or Advanced Purchase (AP).
     * @return resType the type of the reservation.
     */
    public String getResType() {
        return resType;
    }

    /**
     * This method returns the check-in date.
     * @return checkInDate the check-in date for the reservation.
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * This method returns the check-out date.
     * @return checkOutDate the check-out date for the reservation.
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * This method returns the number of rooms reserved.
     * @return numberOfRooms the number of rooms reserved.
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * This method returns the rooms reserved.
     * @return rooms the rooms reserved.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * This method returns the total cost of the reservation.
     * @return totalCost the total cost of the reservation.
     */
    public double getTotalCost() {
        double totalCost = 0;
        for (Room r : rooms) {
            totalCost += Room.getCostOfRoom(r.findRoomType().getDailyRates(), checkInDate, checkOutDate);
        }
        if (resType.equals("AP")) {
            totalCost *= 0.95;
        }
        return totalCost;
    }

    /**
     * This method sets the rooms reserved.
     * @param rooms an ArrayList of type Room of rooms.
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * This method sets the total cost of the reservation.
     * @param totalCost the total cost to be set.
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * This method returns a String that lists all the details of the reservation and is compatible with a csv file.
     */
    @Override
    public String toString() {
        StringBuilder reservationInfo = new StringBuilder(resNumber + "," + resName + "," + resType + "," + checkInDate.toString() + "," + checkOutDate.toString()
                + "," + numberOfRooms + ",");

        for (int i = 0; i < numberOfRooms; i++) {
            if (i == numberOfRooms - 1) {
                reservationInfo.append(rooms.get(i).getRoomType()).append(",").append(rooms.get(i).getRoomOccupancy());
            } else {
                reservationInfo.append(rooms.get(i).getRoomType()).append(",").append(rooms.get(i).getRoomOccupancy()).append(",");
            }
        }

        reservationInfo.append(",").append(totalCost);

        return reservationInfo.toString();
    }

    /**
     * This method returns a String formatted nicely to display all reservation details.
     */
    public String reservationFormat() {
        // Done quickly, may have to update the rooms bit
        return String.format("Reservation Number: %d, Reservation Name: %s, Reservation Type: %s\n" +
                        "Check-in Date: %s, Check-out Date: %s\n" +
                        "Number of Rooms: %d, Rooms: %s, Total Cost: %.2f",
                resNumber, resName, resType, checkInDate, checkOutDate, numberOfRooms, rooms, totalCost);
    }
}
