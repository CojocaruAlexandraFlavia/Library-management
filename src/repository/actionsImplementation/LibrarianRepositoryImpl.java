package repository.actionsImplementation;

import models.Librarian;
import repository.actions.LibrarianRepository;
import utils.DBConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.Queries.*;

public class LibrarianRepositoryImpl implements LibrarianRepository {

    DBConnection dbConnection = DBConnection.getInstance();

    private LibrarianRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final LibrarianRepositoryImpl INSTANCE = new LibrarianRepositoryImpl();
    }

    public static LibrarianRepositoryImpl getInstance(){
        return LibrarianRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }


    @Override
    public List<Librarian> retrieveAllLibrarians() {
        List<Librarian> librarianList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(RETRIEVE_ALL_LIBRARIANS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               Librarian librarian = new Librarian(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"), resultSet.getDate("hiringDate"));
               librarianList.add(librarian);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return librarianList;
    }

    @Override
    public int addLibrarian(Librarian librarian, Optional<Integer> libraryId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_NEW_LIBRARIAN, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, librarian.getFirstName());
            preparedStatement.setString(2, librarian.getLastName());
            preparedStatement.setString(3, librarian.getPhoneNumber());
            preparedStatement.setDate(4,  Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(librarian.getHiringDate())));

            if(libraryId.isPresent()){
                preparedStatement.setInt(5, libraryId.get());
            }else{
                preparedStatement.setNull(5, Types.NULL);
            }

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return Integer.parseInt(resultSet.getString(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public void updateLibrarian(int librarianId, Librarian newLibrarian) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_LIBRARIAN);
            preparedStatement.setString(1, newLibrarian.getFirstName());
            preparedStatement.setString(2, newLibrarian.getLastName());
            preparedStatement.setString(3, newLibrarian.getPhoneNumber());
            preparedStatement.setString(4,  new SimpleDateFormat("dd-MM-yyyy").format(newLibrarian.getHiringDate()));
            preparedStatement.setString(5, String.valueOf(librarianId));
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteLibrarian(int librarianId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_LIBRARIAN);
            preparedStatement.setString(1, String.valueOf(librarianId));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Librarian> getLibrariansForLibrary(int libraryId) {
        List<Librarian> librarians = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_LIBRARIANS_FOR_LIBRARY);
            preparedStatement.setString(1, String.valueOf(libraryId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Librarian librarian = new Librarian(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"), resultSet.getDate("hiringDate"));
                librarians.add(librarian);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return librarians;
    }

    @Override
    public int getLibrarianId(Librarian librarian) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_LIBRARIAN_ID);
            preparedStatement.setString(1, librarian.getFirstName());
            preparedStatement.setString(2, librarian.getLastName());
            preparedStatement.setString(3, librarian.getPhoneNumber());
            preparedStatement.setDate(4, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(librarian.getHiringDate())));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Librarian getLibrarianById(int id) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_LIBRARIAN_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Librarian(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"), resultSet.getDate("hiringDate"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Librarian("", "", "", new java.util.Date());
    }
}
