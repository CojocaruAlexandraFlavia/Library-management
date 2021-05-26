package repository.actionsImplementation;

import models.Address;
import repository.actions.AddressRepository;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class AddressRepositoryImpl implements AddressRepository {

    private final DBConnection dbConnection = DBConnection.getInstance();

    private AddressRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final AddressRepositoryImpl INSTANCE = new AddressRepositoryImpl();
    }

    public static AddressRepositoryImpl getInstance(){
        return AddressRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }


    @Override
    public List<Address> retrieveAllAddresses() {
        List<Address> addressList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(RETRIEVE_ALL_ADDRESSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Address address = new Address(resultSet.getString("street"), resultSet.getString("city"), resultSet.getString("country"), resultSet.getString("zipCode"));
                addressList.add(address);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return addressList;
    }

    @Override
    public int addAddress(Address address) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(INSERT_NEW_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getCountry());
            preparedStatement.setString(4, address.getZipCode());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            int generatedKey = -1;
            if(resultSet.next()){
                generatedKey = Integer.parseInt(resultSet.getString(1));
            }
            return generatedKey;
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }

        //return generatedKey;
    }

    @Override
    public void updateAddress(int addressId, Address newAddress) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_ADDRESS);
            preparedStatement.setString(1, newAddress.getStreet());
            preparedStatement.setString(2, newAddress.getCity());
            preparedStatement.setString(3, newAddress.getCountry());
            preparedStatement.setString(4, newAddress.getZipCode());
            preparedStatement.setString(5, String.valueOf(addressId));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAddress(int addressId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_ADDRESS);
            preparedStatement.setInt(1, addressId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Address getAddressById(int addressId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_ADDRESS_BY_ID);
            return (Address) preparedStatement.executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new Address("", "", "", "");
    }

    @Override
    public int getAddressId(Address address) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_ADDRESS_ID);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getCountry());
            preparedStatement.setString(4, address.getZipCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return -1;
    }

}
