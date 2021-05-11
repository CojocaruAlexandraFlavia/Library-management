package Classes;

import Enums.ReservationStatus;

import java.util.List;

public class BookReservation {
    private int reservationId;
    private int memberId;
    private ReservationStatus status;
    private BookItem bookItem;

    public BookReservation(int reservationId, int memberId, BookItem bookItem, ReservationStatus status) {
        this.reservationId = reservationId;
        this.memberId = memberId;
        this.bookItem = bookItem;
        this.status = status;
    }

    public BookReservation(){
        this.reservationId = -1;
        this.memberId = -1;
        this.bookItem = new BookItem();
        this.status = ReservationStatus.WAITING;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
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
                ", bookItem=" + bookItem +
                '}';
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
