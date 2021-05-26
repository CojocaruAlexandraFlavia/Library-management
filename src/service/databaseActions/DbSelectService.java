package service.databaseActions;

import models.*;
import repository.actionsImplementation.*;
import utils.DBConnection;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DbSelectService {

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
    private final ReservationRepositoryImpl reservationRepository = ReservationRepositoryImpl.getInstance();
    private final BorrowingRepositoryImpl borrowingRepository = BorrowingRepositoryImpl.getInstance();

    private DbSelectService(){}

    private  static class SINGLETON_HOLDER{
        private static final DbSelectService INSTANCE = new DbSelectService();
    }

    public static DbSelectService getInstance(){
        return DbSelectService.SINGLETON_HOLDER.INSTANCE;
    }

    public List<Address> retrieveAllAddresses(){
        return addressRepository.retrieveAllAddresses();
    }

    public Address getAddressById(int addressId){
        return addressRepository.getAddressById(addressId);
    }

    public List<Author> retrieveAllAuthors(){
        return authorRepository.retrieveAllAuthors();
    }

    public List<Author> getAuthorsForBook(int bookId){
        return authorRepository.getAuthorsForBook(bookId);
    }

    public List<Book> getAllBooksForLibrary(int libraryId){
        return bookRepository.getAllBooksForLibrary(libraryId);
    }

    public List<Category> retrieveAllCategories(){
        return categoryRepository.retrieveAllCategories();
    }

    public Category getCategoryForBook(int bookId){
        return categoryRepository.getCategoryForBook(bookId);
    }

    public List<Librarian> retrieveAllLibrarians(){
        return librarianRepository.retrieveAllLibrarians();
    }

    public List<Librarian> getLibrariansForLibrary(int libraryId){
        return librarianRepository.getLibrariansForLibrary(libraryId);
    }

    public List<Library> retrieveAllLibraries(){
        return libraryRepository.retrieveAllLibraries();
    }

    public List<PublishingHouse> retrieveAllPublishingHouses(){
        return publishingHouseRepository.retrieveAllPublishingHouses();
    }

    public PublishingHouse getPublishingHouseForBook(int bookId){
        return publishingHouseRepository.getPublishingHouseForBook(bookId);
    }

    public int getAddressId(Address address){
        return addressRepository.getAddressId(address);
    }

    public int getBookId(Book book){
        return bookRepository.getBookId(book);
    }

    public int getCategoryId(Category category){
        return categoryRepository.getCategoryId(category);
    }

    public int getLibrarianId(Librarian librarian){
        return librarianRepository.getLibrarianId(librarian);
    }

    public int getLibraryIdForBook(Book book){
        return libraryRepository.getLibraryIdForBook(book);
    }

    public int getPublishingHouseId(PublishingHouse publishingHouse){
        return publishingHouseRepository.getPublishingHouseId(publishingHouse);
    }

    public int getNumberOfExemplarsForATitle(String title){
        return bookItemRepository.getNumberOfExemplarsForATitle(title);
    }

    public List<Book> retrieveAllBooksSortedByTitle(){
        return bookRepository.retrieveAllBooks().stream().
                sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
    }

    public Set<String> getAvailableTitles(){
        return bookItemRepository.getAvailableTitles();
    }

    public List<String> getTitlesForCategory(String category){
        return bookRepository.getTitlesForCategory(category);
    }

    public double valueOfPurchasedBooks(){
        return bookItemRepository.valueOfPurchasedBooks();
    }

    public Author getAuthorByFirstAndLastName(String firstName, String lastName){
        return authorRepository.getAuthorByFirstAndLastName(firstName, lastName);
    }

    public PublishingHouse getByName(String name){
        return publishingHouseRepository.getByName(name);
    }

    public Category getCategoryByName(String name){
        return  categoryRepository.getCategoryByName(name);
    }

    public Member verifyMemberId(int id){
        return memberRepository.verifyMemberId(id);
    }

    public BookItem getItemById(int id){
        return bookItemRepository.getItemById(id);
    }

    public BookReservation searchReservationById(int id){
        return reservationRepository.searchReservationById(id);
    }

    public List<Member> retrieveAllMembers(){
        return memberRepository.retrieveAllMembers();
    }

    int getMemberId(Member member){
        return memberRepository.getMemberId(member);
    }

    public Librarian getLibrarianById(int id){
        return librarianRepository.getLibrarianById(id);
    }
}
