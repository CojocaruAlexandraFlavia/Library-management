package Interfaces;

import Classes.Book;

import java.util.List;
import java.util.Set;

public interface Search {
    Book searchBookByTitle(String title);
    Set<Book> searchBookByAuthor(String firstName, String lastName);
    Set<Book> searchBookByCategory(String categoryName);
}
