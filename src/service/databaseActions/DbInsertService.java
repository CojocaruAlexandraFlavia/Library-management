package service.databaseActions;

import enums.BookStatus;
import models.*;
import repository.actionsImplementation.*;
import utils.DBConnection;

import java.util.Date;
import java.util.Optional;

public class DbInsertService {

    private final DBConnection dbConnection = DBConnection.getInstance();
    private final BookRepositoryImpl bookRepository = BookRepositoryImpl.getInstance();
    private final AddressRepositoryImpl addressRepository = AddressRepositoryImpl.getInstance();
    private final AuthorRepositoryImpl authorRepository = AuthorRepositoryImpl.getInstance();
    private final CategoryRepositoryImpl categoryRepository = CategoryRepositoryImpl.getInstance();
    private final LibrarianRepositoryImpl librarianRepository = LibrarianRepositoryImpl.getInstance();
    private final LibraryRepositoryImpl libraryRepository = LibraryRepositoryImpl.getInstance();
    private final PublishingHouseRepositoryImpl publishingHouseRepository = PublishingHouseRepositoryImpl.getInstance();
    private final BookItemRepositoryImpl bookItemRepository = BookItemRepositoryImpl.getInstance();
    private final MemberRepositoryImpl memberRepository = MemberRepositoryImpl.getInstance();
    private final BorrowingRepositoryImpl borrowingRepository = BorrowingRepositoryImpl.getInstance();
    private final ReservationRepositoryImpl reservationRepository = ReservationRepositoryImpl.getInstance();

    private DbInsertService(){}

    private  static class SINGLETON_HOLDER{
        private static final DbInsertService INSTANCE = new DbInsertService();
    }

    public static DbInsertService getInstance(){
        return DbInsertService.SINGLETON_HOLDER.INSTANCE;
    }

    public int addAddress(Address address){
        return addressRepository.addAddress(address);
    }

    public int addAuthor(Author author){
        return authorRepository.addAuthor(author);
    }

    public int addCategory(Category category){
        return categoryRepository.addCategory(category);
    }

    public int addLibrarian(Librarian librarian, Optional<Integer> libraryId){
        return librarianRepository.addLibrarian(librarian, libraryId);
    }

    public int addNewLibrary(Library library){
        return libraryRepository.addNewLibrary(library);
    }

    public int addPublishingHouse(PublishingHouse publishingHouse){
        return publishingHouseRepository.addPublishingHouse(publishingHouse);
    }

    public void addBookItem(int bookId, Date purchaseDate, BookStatus bookStatus){
        bookItemRepository.addBookItem(bookId, purchaseDate, bookStatus);
    }

    public int addBook(Book book, Optional<Integer> libraryId){
        return bookRepository.addBook(book, libraryId);
    }

    public void addNewMember(Member member){
        memberRepository.addNewMember(member);
    }

    public void addBorrowing(BookBorrowing bookBorrowing){
        borrowingRepository.addBorrowing(bookBorrowing);
    }

    public void addReservation(BookReservation reservation){
        reservationRepository.addReservation(reservation);
    }
}
