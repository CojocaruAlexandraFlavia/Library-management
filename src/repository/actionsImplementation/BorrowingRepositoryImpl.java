package repository.actionsImplementation;

import enums.BookStatus;
import models.BookBorrowing;
import repository.actions.BorrowingRepository;
import utils.DBConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import static utils.Queries.ADD_BORROWING;

public class BorrowingRepositoryImpl implements BorrowingRepository {

    DBConnection dbConnection = DBConnection.getInstance();

    private BorrowingRepositoryImpl(){}
    private  static class SINGLETON_HOLDER{
        private static final BorrowingRepositoryImpl INSTANCE = new BorrowingRepositoryImpl();
    }
    public static BorrowingRepositoryImpl getInstance(){
        return BorrowingRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }

    BookItemRepositoryImpl bookItemRepository = BookItemRepositoryImpl.getInstance();

    @Override
    public void addBorrowing(BookBorrowing bookBorrowing) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_BORROWING);
            preparedStatement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(bookBorrowing.getDueDate())));
            preparedStatement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(bookBorrowing.getBorrowingDay())));
            preparedStatement.setInt(3, bookBorrowing.getMemberId());
            preparedStatement.setInt(4, bookBorrowing.getItemId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        bookItemRepository.updateItemStatus(bookBorrowing.getItemId(), BookStatus.BORROWED);
    }
}
