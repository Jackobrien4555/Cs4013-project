import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Reservation class defines and stores a reservation
 * @author David Walsh 20276885
 */

public class Reservation {
    private int resNumber;
    private String resName, resType;
    private LocalDate checkInDate, checkOutDate; // Can change the check in dates to Strings if ye want
    private int numberOfRooms;
    private ArrayList<Room> rooms;
    private double totalCost;

    public Reservation(int resNumber, String resName, String resType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfRooms) {
        this.resNumber = resNumber;
        this.resName = resName;
        this.resType = resType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfRooms = numberOfRooms;
    }

    public Reservation(int resNumber, String resName, String resType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfRooms, ArrayList<Room> rooms) {
        this.resNumber = resNumber;
        this.resName = resName;
        this.resType = resType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfRooms = numberOfRooms;
        this.rooms = rooms;
    }

    public int getResNumber() {
        return resNumber;
    }

    public String getResName() {
        return resName;
    }

    public String getResType() {
        return resType;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void applyAPDiscount() {
        totalCost = totalCost - (totalCost * 0.05);
    }

    public String toString() {
        // Will be making this look nicer but its a start
        return String.format("%d, %s, %s, %s, %d", resNumber, resName, resType, checkInDate, checkOutDate, numberOfRooms);
    }
}