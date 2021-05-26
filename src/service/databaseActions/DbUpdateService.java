package service.databaseActions;

import enums.BookStatus;
import enums.ReservationStatus;
import models.*;
import repository.actionsImplementation.*;
import utils.DBConnection;

public class DbUpdateService {

    private final DBConnection dbConnection = DBConnection.getInstance();
    private final BookRepositoryImpl bookRepository = BookRepositoryImpl.getInstance();
    private final CategoryRepositoryImpl categoryRepository = CategoryRepositoryImpl.getInstance();
    private final BookItemRepositoryImpl bookItemRepository = BookItemRepositoryImpl.getInstance();
    private final AddressRepositoryImpl addressRepository = AddressRepositoryImpl.getInstance();
    private final AuthorRepositoryImpl authorRepository = AuthorRepositoryImpl.getInstance();
    private final LibrarianRepositoryImpl librarianRepository = LibrarianRepositoryImpl.getInstance();
    private final LibraryRepositoryImpl libraryRepository = LibraryRepositoryImpl.getInstance();
    private final PublishingHouseRepositoryImpl publishingHouseRepository = PublishingHouseRepositoryImpl.getInstance();
    private final MemberRepositoryImpl memberRepository = MemberRepositoryImpl.getInstance();
    private final ReservationRepositoryImpl reservationRepository = ReservationRepositoryImpl.getInstance();

    private DbUpdateService(){}

    private  static class SINGLETON_HOLDER{
        private static final DbUpdateService INSTANCE = new DbUpdateService();
    }

    public static DbUpdateService getInstance(){
        return DbUpdateService.SINGLETON_HOLDER.INSTANCE;
    }


    public void updateAddress(int addressId, Address newAddress){
        addressRepository.updateAddress(addressId, newAddress);
    }

    public void updateAuthor(int authorId, Author newAuthor){
        authorRepository.updateAuthor(authorId, newAuthor);
    }

    public void updateLibrarian(int librarianId, Librarian newLibrarian){
        librarianRepository.updateLibrarian(librarianId, newLibrarian);
    }

    public void updatePublishingHouse(int publishingHouseId, PublishingHouse newPublishingHouse){
        publishingHouseRepository.updatePublishingHouse(publishingHouseId, newPublishingHouse);
    }

    public void updateLibrary(int libraryId, Library newLibrary){
        libraryRepository.updateLibrary(libraryId, newLibrary);
    }

    public int closeMemberAccount(int memberId){
        return memberRepository.closeMemberAccount(memberId);
    }

    public void buyAReservedItem(BookReservation reservation){
        reservationRepository.buyAReservedItem(reservation);
    }

    public void updateReservationStatus(int id, ReservationStatus status){
        reservationRepository.updateReservationStatus(id, status);
    }

    void reserveItem(int id){
        bookItemRepository.updateItemStatus(id, BookStatus.RESERVED);
    }
    void buyItem(int id){
        bookItemRepository.updateItemStatus(id, BookStatus.BOUGHT);
    }

    public void updateCategory(int id, String name){
        categoryRepository.updateCategory(id, name);
    }
}
