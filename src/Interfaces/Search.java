package Interfaces;

import Classes.Book;
import Classes.BookItem;
import Classes.BookReservation;

import java.util.Set;

public interface Search {
    int searchBookByTitle(String title);
    Set<Book> searchBookByCategory(String categoryName);
    BookItem searchBookItemById(int id);
    BookReservation searchReservationById(int id);
}
