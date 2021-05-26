package repository.actions;

import models.Address;
import models.Author;

import java.util.List;

public interface AddressRepository {

    List<Address> retrieveAllAddresses();
    int addAddress(Address address);
    void updateAddress(int addressId, Address newAddress);
    void deleteAddress(int addressId);
    Address getAddressById(int addressId);
    int getAddressId(Address address);
}
