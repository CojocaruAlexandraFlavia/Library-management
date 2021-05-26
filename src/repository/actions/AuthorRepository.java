package repository.actions;

import models.Address;
import models.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> retrieveAllAuthors();
    int addAuthor(Author author);
    void updateAuthor(int authorId, Author newAuthor);
    void deleteAuthor(int authorId);
    List<Author> getAuthorsForBook(int bookId);
    int getAuthorId(Author author);
    Author getAuthorByFirstAndLastName(String firstName, String lastName);
}
