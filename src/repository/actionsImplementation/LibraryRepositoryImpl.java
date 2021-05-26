package repository.actionsImplementation;

import models.Address;
import models.Book;
import models.Librarian;
import models.Library;
import repository.actions.LibrarianRepository;
import repository.actions.LibraryRepository;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.Queries.*;


/**
 * Adaug un service cu un private repository
 * rescriu metodele cu {return repository.<nume metoda></}
 */


public class LibraryRepositoryImpl implements LibraryRepository {

    private LibraryRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final LibraryRepositoryImpl INSTANCE = new LibraryRepositoryImpl();
    }

    public static LibraryRepositoryImpl getInstance(){
        return LibraryRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }

    private final DBConnection dbConnection = DBConnection.getInstance();
    private final AddressRepositoryImpl addressRepository = AddressRepositoryImpl.getInstance();
    private final LibrarianRepository librarianRepository = LibrarianRepositoryImpl.getInstance();
    private final BookRepositoryImpl bookRepository = BookRepositoryImpl.getInstance();

    @Override
    public List<Library> retrieveAllLibraries() {
        List<Library> libraries = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(RETRIEVE_ALL_LIBRARIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int libraryId = resultSet.getInt(1);
                Address address = addressRepository.getAddressById(Integer.parseInt(resultSet.getString("addressId")));
                List<Librarian> librarians = librarianRepository.getLibrariansForLibrary(libraryId);
                List<Book> books = bookRepository.getAllBooksForLibrary(libraryId);
                Library library = new Library(resultSet.getString("name"), address, librarians, books);
                libraries.add(library);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return libraries;
    }


    @Override
    public int addNewLibrary(Library library) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_NEW_LIBRARY, Statement.RETURN_GENERATED_KEYS);
            if(addressRepository.getAddressId(library.getAddress()) == -1){
                addressRepository.addAddress(library.getAddress());
            }
            preparedStatement.setInt(1, addressRepository.getAddressId(library.getAddress()));
            preparedStatement.setString(2, library.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            int libraryId = -1;
            if(resultSet.next()){
               libraryId = Integer.parseInt(resultSet.getString(1));
                for (Librarian librarian : library.getLibrarians()){
                    if(librarianRepository.getLibrarianId(librarian) == -1){
                        librarianRepository.addLibrarian(librarian, Optional.of(libraryId));
                    }
                }
                for (Book book : library.getBooks()){
                    if(bookRepository.getBookId(book) == -1){
                        bookRepository.addBook(book, Optional.of(libraryId));
                    }
                }
            }
            return  libraryId;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateLibrary(int libraryId, Library newLibrary) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_LIBRARY);
            preparedStatement.setInt(1, addressRepository.getAddressId(newLibrary.getAddress()));
            preparedStatement.setString(2, newLibrary.getName());
            preparedStatement.setInt(3, libraryId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLibrary(int libraryId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_LIBRARY);
            preparedStatement.setInt(1, libraryId);
            preparedStatement.executeUpdate();
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public int getLibraryIdForBook(Book book) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_LIBRARY_ID_FOR_BOOK);
            preparedStatement.setString(1, book.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }


}
