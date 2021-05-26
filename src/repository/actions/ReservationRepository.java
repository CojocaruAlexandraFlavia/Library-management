package repository.actions;

import enums.ReservationStatus;
import models.BookReservation;

public interface ReservationRepository {
    void addReservation(BookReservation reservation);
    void buyAReservedItem(BookReservation reservation);
    BookReservation searchReservationById(int id);
    void updateReservationStatus(int id, ReservationStatus status);
}
