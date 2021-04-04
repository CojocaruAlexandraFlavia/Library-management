package Classes;

import Enums.ReservationStatus;

import java.util.List;

public class BookReservation {
    private int reservationId;
    private int memberId;
    private ReservationStatus status;
    private List<BookItem> bookItems;

    public BookReservation(int reservationId, int memberId, List<BookItem> bookItems, ReservationStatus status) {
        this.reservationId = reservationId;
        this.memberId = memberId;
        this.bookItems = bookItems;
        this.status = status;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookReservation{" +
                "reservationId=" + reservationId +
                ", status=" + status +
                ", bookItems=" + bookItems +
                '}';
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
