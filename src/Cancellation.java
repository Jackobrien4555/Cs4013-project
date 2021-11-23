import java.time.LocalDate;

/**
 * The Cancellation class
 * @author David Walsh 20276885
 */

public class Cancellation {
    private Reservation reservation;
    private LocalDate cancellationDate;
    private boolean refunded;
    private double income;

    /**
     * Constructor for creating a Cancellation object (used when creating cancellation from user input).
     * @param reservation The reservation that is being cancelled.
     */
    public Cancellation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Constructor for creating a Cancellation object (used when creating a cancellation read from Cancellations.csv file).
     * @param reservation The reservation that is being cancelled.
     * @param cancellationDate The date on which the cancellation was made.
     * @param refunded whether or not the cancellation resulted in a refund.
     */
    public Cancellation(Reservation reservation, LocalDate cancellationDate, boolean refunded) {
        this.reservation = reservation;
        this.cancellationDate = cancellationDate;
        this.refunded = refunded;
    }

    /**
     * Constructor for creating a Cancellation object (used when creating a cancellation read from Cancellations.csv file).
     * @param reservation The reservation that is being cancelled.
     * @param cancellationDate The date on which the cancellation was made.
     * @param refunded whether or not the cancellation resulted in a refund.
     * @param income
     */
    public Cancellation(Reservation reservation, LocalDate cancellationDate, boolean refunded, double income) {
        this.reservation = reservation;
        this.cancellationDate = cancellationDate;
        this.refunded = refunded;
        this.income = income;
    }

    /**
     * This method returns the reservation that belongs to the current cancellation.
     * @return reservation the reservation that belongs to the cancellation.
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Returns the cancelled date.
     * @return cancellationDate the date the cancellation was made.
     */
    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    /**
     * Returns whether or not the cancellation resulted in a refund.
     * @return refunded shows if the cancellation resulted in a refund.
     */
    public boolean getRefunded() {
        return refunded;
    }

    /**
     * Returns the total income of the reservation being cancelled.
     * @return income the total income of the reservation being cancelled.
     */
    public double getIncome() {
        return income;
    }

    /**
     * This method sets the reservation.
     * @param reservation the reservation.
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * This method sets the cancellation date for the reservation.
     * @param cancellationDate the date that the reservation is cancelled.
     */
    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    /**
     * This method sets whether the reservation is refunded or not.
     * @param refunded whether the reservation is refunded or not.
     */
    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    /**
     * This method sets the total income of the reservation.
     * @param income the total income to be set.
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * This method checks whether a reservation is refundable or not.
     */
    public boolean isRefundable() {
        // Need to work on this
        if (reservation.getResType().equalsIgnoreCase("s")/* && cancellationDate < reservation.getCheckInDate()*/) {
            return true;
        }

        return false;
    }

    /**
     * Returns a string that represents the data contained by a cancellation and is compatible with a csv file.
     */
    public String toString() {
        // Will be making this look nicer
        return String.format("Cancellation - Reservation: %s, " +
                "Cancellation Date: %s, " +
                "Refunded: %b, " +
                "Income: %f", reservation, cancellationDate, refunded, income);
    }
}
