package Interfaces;

import Classes.Book;
import Classes.BookItem;
import Classes.BookReservation;

import java.util.List;
import java.util.Set;

public interface Search {
    Book searchBookByTitle(String title);
    Set<Book> searchBookByAuthor(String firstName, String lastName);
    Set<Book> searchBookByCategory(String categoryName);
    BookItem searchBookItemById(int id);
    BookReservation searchReservationById(int id);
}
