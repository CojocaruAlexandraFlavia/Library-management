package repository.actionsImplementation;

import models.PublishingHouse;
import repository.actions.PublishingHouseRepository;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class PublishingHouseRepositoryImpl implements PublishingHouseRepository {


    private PublishingHouseRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final PublishingHouseRepositoryImpl INSTANCE = new PublishingHouseRepositoryImpl();
    }

    public static PublishingHouseRepositoryImpl getInstance(){
        return PublishingHouseRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }

    private final DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public List<PublishingHouse> retrieveAllPublishingHouses() {
        List<PublishingHouse> publishingHouses = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(RETRIEVE_ALL_PUBLISHING_HOUSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                PublishingHouse publishingHouse = new PublishingHouse(resultSet.getString("name"), resultSet.getString("description"));
                publishingHouses.add(publishingHouse);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return publishingHouses;
    }

    @Override
    public int addPublishingHouse(PublishingHouse publishingHouse) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_PUBLISHING_HOUSE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, publishingHouse.getName());
            preparedStatement.setString(2, publishingHouse.getDescription());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return Integer.parseInt(resultSet.getString(1));
            }

        }catch (SQLException e){
            System.out.println("The publishing house already exists.");
        }

        return -1;
    }

    @Override
    public void updatePublishingHouse(int publishingHouseId, PublishingHouse newPublishingHouse) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_PUBLISHING_HOUSE);
            preparedStatement.setString(1, newPublishingHouse.getName());
            preparedStatement.setString(2, newPublishingHouse.getDescription());
            preparedStatement.setString(3, String.valueOf(publishingHouseId));
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void deletePublishingHouse(int publishingHouseId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_PUBLISHING_HOUSE);
            preparedStatement.setString(1, String.valueOf(publishingHouseId));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public PublishingHouse getPublishingHouseForBook(int bookId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_PUBLISHING_HOUSE_FOR_BOOK);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new PublishingHouse(resultSet.getString("name"), resultSet.getString("description"));
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
        return new PublishingHouse("", "");
    }

    @Override
    public int getPublishingHouseId(PublishingHouse publishingHouse) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_PUBLISHING_HOUSE_ID);
            preparedStatement.setString(1, publishingHouse.getName());
            preparedStatement.setString(2, publishingHouse.getDescription());
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
    public PublishingHouse getByName(String name) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_PH_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new PublishingHouse(resultSet.getString("name"), resultSet.getString("description"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  new PublishingHouse("", "");
    }
}
