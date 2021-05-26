package repository.actionsImplementation;

import com.sun.source.tree.PackageTree;
import enums.BookStatus;
import enums.MemberStatus;
import models.Author;
import models.BookItem;
import models.Category;
import repository.actions.BookItemRepository;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static utils.Queries.*;

public class BookItemRepositoryImpl implements BookItemRepository {

    private final DBConnection dbConnection = DBConnection.getInstance();

    private BookItemRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final BookItemRepositoryImpl INSTANCE = new BookItemRepositoryImpl();
    }

    public static BookItemRepositoryImpl getInstance(){
        return BookItemRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }

    @Override
    public void addBookItem(int bookId, Date purchaseDate, BookStatus bookStatus) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_BOOK_ITEM);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setDate(2, new java.sql.Date(purchaseDate.getTime()));
            preparedStatement.setString(3, bookStatus.toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getNumberOfExemplarsForATitle(String title) {
        int nr = 0;
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_NR_OF_EXEMPLARS_FOR_TITLE);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               nr = Integer.parseInt(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nr;
    }

    @Override
    public Set<String> getAvailableTitles() {
        Set<String> titles = new HashSet<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_AVAILABLE_TITLES);
            preparedStatement.setString(1, String.valueOf(BookStatus.AVAILABLE));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                titles.add(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return titles;
    }

    @Override
    public double valueOfPurchasedBooks() {
        double value = 0;
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_VALUE_OF_PURCHASED_BOOKS);
            preparedStatement.setString(1, String.valueOf(BookStatus.BOUGHT));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                value +=  Double.parseDouble(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public BookItem getItemById(int id) {
        BookItem item = new BookItem();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_ITEM_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                item.setItemId(id);
                item.setBookStatus(BookStatus.valueOf(resultSet.getString("status")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void updateItemStatus(int id, BookStatus status) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_ITEM_STATUS);
            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            if(String.valueOf(status).equals("BOUGHT")){
                updatePurchasedDate(id, java.sql.Date.valueOf((LocalDate.now())));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePurchasedDate(int id, java.sql.Date date) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_ITEM_DATE);
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
