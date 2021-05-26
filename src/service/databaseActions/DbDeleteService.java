package service.databaseActions;

import models.Book;
import repository.actionsImplementation.*;

public class DbDeleteService {

    private final AddressRepositoryImpl addressRepository = AddressRepositoryImpl.getInstance();
    private final AuthorRepositoryImpl authorRepository = AuthorRepositoryImpl.getInstance();
    private final CategoryRepositoryImpl categoryRepository = CategoryRepositoryImpl.getInstance();
    private final LibrarianRepositoryImpl librarianRepository = LibrarianRepositoryImpl.getInstance();
    private final LibraryRepositoryImpl libraryRepository = LibraryRepositoryImpl.getInstance();
    private final PublishingHouseRepositoryImpl publishingHouseRepository = PublishingHouseRepositoryImpl.getInstance();
    private final BookRepositoryImpl bookRepository = BookRepositoryImpl.getInstance();
    private final MemberRepositoryImpl memberRepository = MemberRepositoryImpl.getInstance();

    private DbDeleteService(){}

    private  static class SINGLETON_HOLDER{
        private static final DbDeleteService INSTANCE = new DbDeleteService();
    }

    public static DbDeleteService getInstance(){
        return DbDeleteService.SINGLETON_HOLDER.INSTANCE;
    }

    public void deleteAddress(int addressId){
        addressRepository.deleteAddress(addressId);
    }

    public void deleteAuthor(int authorId){
        authorRepository.deleteAuthor(authorId);
    }

    public void deleteCategory(int categoryId){
        categoryRepository.deleteCategory(categoryId);
    }

    public void deleteLibrarian(int librarianId){
        librarianRepository.deleteLibrarian(librarianId);
    }

    public void deleteLibrary(int libraryId){
        libraryRepository.deleteLibrary(libraryId);
    }

    public void deletePublishingHouse(int publishingHouseId){
        publishingHouseRepository.deletePublishingHouse(publishingHouseId);
    }

//    public void deleteLibraryWithFewestTitles(){
//        libraryRepository.deleteLibraryWithFewestBooks();
//    }
    public void deleteBook(Book book){
        bookRepository.deleteBook(book);
    }

    void deleteMember(int id){
        memberRepository.deleteMember(id);
    };
}
