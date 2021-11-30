import java.util.ArrayList;

/**
 * This class keeps track of all the reservations, cancellations and users and
 * provides numerous functionalities for these lists.
 *
 * @author Edison Cai 20241135
 */
public abstract class ReservationCancellationManager {
    private static ArrayList<Reservation> allReservations;
    private static ArrayList<Cancellation> allCancellations;
    private static ArrayList<User> allUsers;

    /**
     * Returns list of all users.
     * @return An ArrayList of all Users.
     */
    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * Set list of all users.
     * @param allUsers An ArrayList of Users.
     */
    public static void setAllUsers(ArrayList<User> allUsers) {
        ReservationCancellationManager.allUsers = allUsers;
    }

    /**
     * Getting a Reservation object from the reservation number.
     * @param resNumber Reservation number.
     * @return The Reservation object with corresponding reservation number.
     */
    public static Reservation getReservation(int resNumber) {
        for (Reservation Reservation : allReservations) {
            if (resNumber == Reservation.getResNumber()) {
                return Reservation;
            }
        }
        return null;
    }


    /**
     * Gets list of reservations.
     *
     * @return List of all reservations.
     */
    public static ArrayList<Reservation> getAllReservations() {
        return allReservations;
    }


    /**
     * Setting the list of all reservations.
     *
     * @param allReservations List of reservations.
     */
    public static void setAllReservations(ArrayList<Reservation> allReservations) {
        ReservationCancellationManager.allReservations = allReservations;
    }

    /**
     * Gets list of cancellations.
     *
     * @return List of all cancellations
     */
    public static ArrayList<Cancellation> getAllCancellations() {
        return allCancellations;
    }


    /**
     * Setting the list of all cancellations.
     *
     * @param allCancellations List of cancellations.
     */
    public static void setAllCancellations(ArrayList<Cancellation> allCancellations) {
        ReservationCancellationManager.allCancellations = allCancellations;
    }

    /**
     * Adding a Reservation to allReservations.
     *
     * @param res Reservation to be added.
     */
    public static void addReservation(Reservation res) {
        allReservations.add(res);
    }

    /**
     * Adding a Cancellation to allCancellations.
     *
     * @param can Cancellation to be added.
     */
    public static void addCancellation(Cancellation can) {
        allCancellations.add(can);
    }
}
