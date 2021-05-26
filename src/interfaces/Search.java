package interfaces;

import models.Book;
import models.BookItem;
import models.BookReservation;

import java.util.Set;

public interface Search {
    int searchBookByTitle(String title);
    Set<Book> searchBookByCategory(String categoryName);
    BookItem searchBookItemById(int id);
    BookReservation searchReservationById(int id);
}
