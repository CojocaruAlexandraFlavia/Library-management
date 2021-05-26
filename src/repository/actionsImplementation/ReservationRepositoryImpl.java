package repository.actionsImplementation;

import enums.BookStatus;
import enums.ReservationStatus;
import models.BookItem;
import models.BookReservation;
import repository.actions.BookItemRepository;
import repository.actions.ReservationRepository;
import utils.DBConnection;
import static utils.Queries.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final DBConnection dbConnection = DBConnection.getInstance();
    BookItemRepositoryImpl bookItemRepository = BookItemRepositoryImpl.getInstance();

    private ReservationRepositoryImpl(){}
    private  static class SINGLETON_HOLDER{
        private static final ReservationRepositoryImpl INSTANCE = new ReservationRepositoryImpl();
    }
    public static ReservationRepositoryImpl getInstance(){
        return ReservationRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }

    @Override
    public void addReservation(BookReservation reservation) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_RESERVATION);
            preparedStatement.setInt(1, reservation.getMemberId());
            preparedStatement.setInt(2, reservation.getBookItem().getItemId());
            preparedStatement.setString(3, String.valueOf(ReservationStatus.WAITING));
            preparedStatement.executeUpdate();
            bookItemRepository.updateItemStatus(reservation.getBookItem().getItemId(), BookStatus.RESERVED);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void buyAReservedItem(BookReservation reservation) {
        BookItem item = reservation.getBookItem();
        bookItemRepository.updateItemStatus(item.getItemId(), BookStatus.BOUGHT);

        updateReservationStatus(reservation.getReservationId(), ReservationStatus.COMPLETED);
    }

    @Override
    public BookReservation searchReservationById(int id) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_RESERVATION_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                BookItem item = bookItemRepository.getItemById(Integer.parseInt(resultSet.getString("itemId")));
                return new BookReservation(Integer.parseInt(resultSet.getString("id")), Integer.parseInt(resultSet.getString("memberId")), item, ReservationStatus.valueOf(resultSet.getString("status")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new BookReservation(-1, -1, new BookItem(), ReservationStatus.PENDING);
    }

    @Override
    public void updateReservationStatus(int id, ReservationStatus status) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_RESERVATION_STATUS);
            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
