import java.time.LocalDate;

/**
 * The Cancellation class
 * @author David Walsh 20276885
 */

public class Cancellation {
    private Reservation reservation;
    private LocalDate cancellationDate;
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
     */
    public Cancellation(Reservation reservation, LocalDate cancellationDate) {
        this.reservation = reservation;
        this.cancellationDate = cancellationDate;
    }

    /**
     * Constructor for creating a Cancellation object (used when creating a cancellation read from Cancellations.csv file).
     * @param reservation The reservation that is being cancelled.
     * @param cancellationDate The date on which the cancellation was made.
     * @param income The income generated from a reservation, its 0 it is refunded
     */
    public Cancellation(Reservation reservation, LocalDate cancellationDate, double income) {
        this.reservation = reservation;
        this.cancellationDate = cancellationDate;
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
        String reservationString = reservation.toString();
        return reservationString + "," + LocalDate.now().toString() + "," + income;
    }

    public String cancellationFormat() {
        // Done quickly, may have to update the rooms bit
        return String.format("CANCELLATION -\n" +
                        "Reservation Number: %d, Reservation Name: %s, Reservation Type: %s\n" +
                        "Check-in Date: %s, Check-out Date: %s\n" +
                        "Number of Rooms: %d, Rooms: %s, Total Cost: %.2f\n" +
                        "Cancellation Date: %s, Income: %.2f",
                reservation.getResNumber(), reservation.getResName(), reservation.getResType(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getNumberOfRooms(), reservation.getRooms(), reservation.getTotalCost(), cancellationDate, income);
    }
}
