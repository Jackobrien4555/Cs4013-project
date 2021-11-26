import java.util.ArrayList;

/**
 * This class keeps track of all the reservations and
 * provides numerous functionalities for these reservations.
 *
 * @author Edison Cai 20241135
 */
public abstract class ReservationCancellationManager {
    private static ArrayList<Reservation> allReservations;
    private static ArrayList<Cancellation> allCancellations;

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
     * @param res Reservation to be added.
     */
    public static void addReservation(Reservation res) {
        allReservations.add(res);
    }

    /**
     * Adding a Cancellation to allCancellations.
     * @param can Cancellation to be added.
     */
    public static void addCancellation(Cancellation can) {
        allCancellations.add(can);
    }
}
