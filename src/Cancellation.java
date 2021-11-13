import java.time.LocalDate;

/**
 * The Cancellation class
 * @author David Walsh 20276885
 */

public class Cancellation {
    private Reservation reservation;
    private LocalDate cancellationDate;
    private boolean refunded;
    private int income; // Not 100% sure what I need to do with this yet

    public Cancellation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Cancellation(Reservation reservation, LocalDate cancellationDate, boolean refunded) {
        this.reservation = reservation;
        this.cancellationDate = cancellationDate;
        this.refunded = refunded;
    }

    public Cancellation(Reservation reservation, LocalDate cancellationDate, boolean refunded, int income) {
        this.reservation = reservation;
        this.cancellationDate = cancellationDate;
        this.refunded = refunded;
        this.income = income;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    public boolean getRefunded() {
        return refunded;
    }

    public int getIncome() {
        return income;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public boolean isRefundable() {
        if (reservation.getResType().equalsIgnoreCase("s")/* && cancellationDate < reservation.getCheckInDate()*/) {
            return true;
        }

        return false;
    }

    public String toString() {
        return "";
    }
}