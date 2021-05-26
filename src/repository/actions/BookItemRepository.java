package repository.actions;

import enums.BookStatus;
import enums.ReservationStatus;
import models.BookItem;
import models.BookReservation;
import repository.actionsImplementation.BookItemRepositoryImpl;

import java.util.Date;
import java.util.Set;

public interface BookItemRepository {

    void addBookItem(int bookId, Date purchaseDate, BookStatus bookStatus);
    int getNumberOfExemplarsForATitle(String title);
    Set<String> getAvailableTitles();
    double valueOfPurchasedBooks();
    BookItem getItemById(int id);
    void updateItemStatus(int id, BookStatus status);
    void updatePurchasedDate(int id, java.sql.Date date);
}
