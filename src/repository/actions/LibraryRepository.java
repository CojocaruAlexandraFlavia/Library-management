package repository.actions;

import models.Book;
import models.Library;

import java.util.List;

public interface LibraryRepository {

    List<Library> retrieveAllLibraries();
    int addNewLibrary(Library library);
    void updateLibrary(int libraryId, Library newLibrary);
    void deleteLibrary(int libraryId);
    int getLibraryIdForBook(Book book);
}
