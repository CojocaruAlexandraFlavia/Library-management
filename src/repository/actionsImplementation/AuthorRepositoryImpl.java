package repository.actionsImplementation;

import models.Address;
import models.Author;
import repository.actions.AuthorRepository;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static utils.Queries.*;
public class AuthorRepositoryImpl implements AuthorRepository {


    private AuthorRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final AuthorRepositoryImpl INSTANCE = new AuthorRepositoryImpl();
    }

    public static AuthorRepositoryImpl getInstance(){
        return AuthorRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }


    private final DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public List<Author> retrieveAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(RETRIEVE_ALL_AUTHORS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Author author = new Author(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"));
                authors.add(author);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return authors;
    }

    @Override
    public int addAuthor(Author author) {
        Author author1 = getAuthorByFirstAndLastName(author.getFirstName(), author.getLastName());
        System.out.println("autor first name = " + author.getFirstName());
        System.out.println("author1 first name = " + author1.getFirstName());
        System.out.println("autor last name = " + author.getLastName());
        System.out.println("author1 last name = " + author1.getLastName());
        System.out.println("autor phone = " + author.getPhoneNumber());
        System.out.println("author1 ph = " + author1.getPhoneNumber());


        if(author.getFirstName().equals(author1.getFirstName()) && author1.getLastName().equals(author.getLastName()) && author1.getPhoneNumber().equals(author.getPhoneNumber())){
            return -1;
        }
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_AUTHOR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getPhoneNumber());
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
    public void updateAuthor(int authorId, Author newAuthor) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_AUTHOR);
            preparedStatement.setString(1, newAuthor.getFirstName());
            preparedStatement.setString(2, newAuthor.getLastName());
            preparedStatement.setString(3, newAuthor.getPhoneNumber());
            preparedStatement.setString(4, String.valueOf(authorId));
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteAuthor(int authorId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_AUTHOR);
            preparedStatement.setString(1, String.valueOf(authorId));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> getAuthorsForBook(int bookId) {
        List<Author> authors = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_AUTHORS_FOR_BOOK);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Author author = new Author(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"));
                authors.add(author);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  authors;
    }

    @Override
    public int getAuthorId(Author author) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_AUTHOR_ID);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getPhoneNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Integer.parseInt(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Author getAuthorByFirstAndLastName(String firstName, String lastName) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_AUTHOR_BY_FIRSTNAME_LASTNAME);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Author(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Author("", "", "");
    }

}
