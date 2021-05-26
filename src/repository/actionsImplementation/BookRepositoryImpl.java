package repository.actionsImplementation;

import enums.BookStatus;
import models.*;
import repository.actions.BookAuthorRepository;
import repository.actions.BookRepository;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.Queries.*;

public class BookRepositoryImpl implements BookRepository {

    private final DBConnection dbConnection = DBConnection.getInstance();
    private final CategoryRepositoryImpl categoryRepository = CategoryRepositoryImpl.getInstance();
    private final PublishingHouseRepositoryImpl publishingHouseRepository = PublishingHouseRepositoryImpl.getInstance();
    private final AuthorRepositoryImpl authorRepository = AuthorRepositoryImpl.getInstance();
    private final BookItemRepositoryImpl bookItemRepository = BookItemRepositoryImpl.getInstance();
    private final BookAuthorRepositoryImpl bookAuthorRepository = BookAuthorRepositoryImpl.getInstance();

    private BookRepositoryImpl(){}
    private  static class SINGLETON_HOLDER{
        private static final BookRepositoryImpl INSTANCE = new BookRepositoryImpl();
    }
    public static BookRepositoryImpl getInstance(){
        return BookRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }

    @Override
    public List<Book> getAllBooksForLibrary(int libraryId) {
        List<Book> books = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_BOOKS_FOR_LIBRARY);
            preparedStatement.setInt(1, libraryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            getBooks(books, resultSet);
        }catch(SQLException exception){
            exception.printStackTrace();
        }
        return  books;
    }

    @Override
    public List<Book> retrieveAllBooks() {
        List<Book> books = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_ALL_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            getBooks(books, resultSet);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    private void getBooks(List<Book> books, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            Category category = categoryRepository.getCategoryForBook(resultSet.getInt(1));
            PublishingHouse publishingHouse = publishingHouseRepository.getPublishingHouseForBook(resultSet.getInt(1));
            List<Author> authors = authorRepository.getAuthorsForBook(resultSet.getInt(1));
            Book book = new Book(resultSet.getString("title"), resultSet.getString("subject"), resultSet.getString("language"), resultSet.getInt("nrOfPages"), publishingHouse,authors,category, resultSet.getDouble("price"), resultSet.getInt("nrOfCopies"));
            books.add(book);
        }
    }

    @Override
    public int addBook(Book book, Optional<Integer> libraryId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getSubject());
            preparedStatement.setString(3, book.getLanguage());
            preparedStatement.setInt(4, book.getNumberOfPages());
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.setInt(6, book.getNrOfCopies());
            int categoryId = categoryRepository.getCategoryId(book.getCategory());
            if( categoryId== -1){
                categoryId = categoryRepository.addCategory(book.getCategory());
            }
            preparedStatement.setInt(7, categoryId );
            if(publishingHouseRepository.getPublishingHouseId(book.getPublishingHouse()) == -1){
                publishingHouseRepository.addPublishingHouse(book.getPublishingHouse());
            }
            preparedStatement.setInt(8, publishingHouseRepository.getPublishingHouseId(book.getPublishingHouse()));
            if(libraryId.isPresent()){
                preparedStatement.setInt(9, libraryId.get());
            }else{
                preparedStatement.setNull(9, Types.NULL);
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int bookId = -1 ;
            if(resultSet.next()){
                bookId = Integer.parseInt(resultSet.getString(1));
            }

            //System.out.println("bookId = " + bookId);
            for(Author author : book.getAuthors()){
                int authorId = authorRepository.getAuthorId(author);
                if(authorId == -1){
                    authorId = authorRepository.addAuthor(author);
                }
                //
                // +System.out.println("authorId = " + authorId);
                bookAuthorRepository.insertAssociativeTable(bookId, authorId);

            }
            return bookId;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getBookId(Book book) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_BOOK_ID);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getSubject());
            preparedStatement.setString(3, book.getLanguage());
            preparedStatement.setInt(4, book.getNumberOfPages());
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.setInt(6, categoryRepository.getCategoryId(book.getCategory()));
            preparedStatement.setInt(7, publishingHouseRepository.getPublishingHouseId(book.getPublishingHouse()));
            //preparedStatement.setInt(8, libraryRepository.getLibraryIdForBook(book));
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
    public List<String> getTitlesForCategory(String category) {
        List<String> titles = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_TITLES_FOR_CATEGORY);
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                titles.add(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  titles;
    }

    @Override
    public void deleteBook(Book book) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_BOOK);
            int bookId = getBookId(book);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }




}
