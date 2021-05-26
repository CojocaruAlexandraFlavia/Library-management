package repository.actions;

import models.Librarian;

import java.util.List;
import java.util.Optional;

public interface LibrarianRepository {

    List<Librarian> retrieveAllLibrarians();
    int addLibrarian(Librarian librarian, Optional<Integer> libraryId);
    void updateLibrarian(int librarianId, Librarian newLibrarian);
    void deleteLibrarian(int librarianId);
    List<Librarian> getLibrariansForLibrary(int libraryId);
    int getLibrarianId(Librarian librarian);
    Librarian getLibrarianById(int id);
}
