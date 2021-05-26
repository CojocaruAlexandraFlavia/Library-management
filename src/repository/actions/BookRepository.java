package repository.actions;

import models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> getAllBooksForLibrary(int libraryId);
    List<Book> retrieveAllBooks();
    int addBook(Book book, Optional<Integer> libraryId);
    int getBookId(Book book);
    List<String> getTitlesForCategory(String category);
    void deleteBook(Book book);
}
