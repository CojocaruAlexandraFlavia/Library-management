package repository.actionsImplementation;

import repository.actions.BookAuthorRepository;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static utils.Queries.INSERT_ASSOCIATIVE_TABLE;

public class BookAuthorRepositoryImpl implements BookAuthorRepository {

    private BookAuthorRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final BookAuthorRepositoryImpl INSTANCE = new BookAuthorRepositoryImpl();
    }

    public static BookAuthorRepositoryImpl getInstance(){
        return BookAuthorRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }


    private final DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public void insertAssociativeTable(int bookId, int authorId) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(INSERT_ASSOCIATIVE_TABLE);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, authorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
