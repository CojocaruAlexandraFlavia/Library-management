package utils;

public class Queries {

    public static final String RETRIEVE_ALL_ADDRESSES = "SELECT * FROM pao.address";
    public static final String INSERT_NEW_ADDRESS = "INSERT INTO address(id, street, city, country, zipCode) values (null, ?, ?, ?, ?)";
    public static final String UPDATE_ADDRESS = "UPDATE address SET street = ?, city = ?, country = ?," +
            " zipCode = ? WHERE  id = ?";
    public static final String DELETE_ADDRESS = "DELETE FROM address WHERE id = ?";
    public static final String GET_ADDRESS_BY_ID = "SELECT * FROM address WHERE id = ?";
    public static final String GET_ADDRESS_ID = "SELECT id FROM address WHERE street = ? and city = ? and country = ? and zipCode = ?";

    public static final String ADD_AUTHOR = "INSERT INTO author(id, firstName, lastName, phoneNumber) " +
            "values (null, ?, ?, ?)";
    public static final String UPDATE_AUTHOR = "UPDATE author SET firstName = ?, lastName = ?, " +
            "phoneNumber = ? WHERE id = ?";
    public static final String RETRIEVE_ALL_AUTHORS = "SELECT * FROM author";
    public static final String DELETE_AUTHOR = "DELETE FROM author WHERE id = ?";
    public static final String GET_AUTHORS_FOR_BOOK = "SELECT * FROM author a join book_author ba on " +
            "(a.id = ba.authorId) WHERE ba.bookId = ?";
    public static final String GET_AUTHOR_ID = "SELECT id FROM author WHERE firstName = ? and lastName = ? " +
            "and phoneNumber = ?";
    public static final String GET_AUTHOR_BY_FIRSTNAME_LASTNAME = "SELECT * from author WHERE firstName = ? and lastName = ?";

    public static final String ADD_CATEGORY = "INSERT INTO category(id, name) values (null, ?)";
    public static final String RETRIEVE_ALL_CATEGORIES = "SELECT * FROM category";
    public static final String DELETE_CATEGORY = "DELETE FROM category WHERE id = ?";
    public static final String GET_CATEGORY_FOR_BOOK = "SELECT * FROM category c JOIN book b on " +
            "(c.id = b.categoryId) WHERE b.id = ?";
    public static final String GET_CATEGORY_ID = "SELECT id FROM category WHERE name = ?";
    public static final String GET_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name = ?";
    public static final String UPDATE_CATEGORY = "UPDATE category SET name = ?";

    public static final String ADD_PUBLISHING_HOUSE = "INSERT INTO publishing_house(name, description) " +
            "VALUES (?, ?)";
    public static final String UPDATE_PUBLISHING_HOUSE = "UPDATE publishing_house SET name = ?, " +
            "description = ? WHERE id = ?";
    public static final String RETRIEVE_ALL_PUBLISHING_HOUSES = "SELECT * FROM publishing_house";
    public static final String DELETE_PUBLISHING_HOUSE = "DELETE FROM publishing_house WHERE id = ?";
    public static final String GET_PUBLISHING_HOUSE_FOR_BOOK = "SELECT * FROM publishing_house ph JOIN " +
            "book b on (b.publishingHouseId = ph.id) WHERE b.id = ?";
    public static final String GET_PUBLISHING_HOUSE_ID = "SELECT id FROM publishing_house WHERE name = ? " +
            "and description = ?";
    public static final String GET_PH_BY_NAME = "SELECT * FROM publishing_house WHERE name = ?";

    public static final String ADD_NEW_LIBRARY = "INSERT INTO library(id, addressId, name) values(null, ?, ?)";
    public static final String UPDATE_LIBRARY = "UPDATE library SET addressId = ?, name = ? WHERE id = ?";
    public static final String RETRIEVE_ALL_LIBRARIES = "SELECT * FROM library";
    public static final String DELETE_LIBRARY = "DELETE FROM library WHERE id = ?";
    public static final String GET_LIBRARY_ID_FOR_BOOK = "SELECT id from library l JOIN book b on (l.id = b.libraryId) WHERE b.title = ?";

    public static final String ADD_NEW_LIBRARIAN = "INSERT INTO librarian(id, firstName, lastName, " +
            "phoneNumber, hiringDate, libraryId) values(null, ?, ?, ?, ?, ?)";
    public static final String UPDATE_LIBRARIAN = "UPDATE librarian SET firstName = ?, lastName = ?, " +
            "phoneNumber = ?, hiringDate = STR_TO_DATE(?, '%d,%m,%Y') WHERE id = ?";
    public static final String RETRIEVE_ALL_LIBRARIANS = "SELECT * FROM librarian";
    public static final String DELETE_LIBRARIAN = "DELETE FROM librarian WHERE id = ?";
    public static final String GET_LIBRARIANS_FOR_LIBRARY = "SELECT * FROM librarian WHERE library = ?";
    public static final String GET_LIBRARIAN_ID = "SELECT id from librarian WHERE firstName = ? and " +
            "lastName = ? and phoneNumber = ? and hiringDate = ?";
    public static final String GET_LIBRARIAN_BY_ID = "SELECT * FROM librarian WHERE id = ?";

    public static final String GET_BOOKS_FOR_LIBRARY = "SELECT * FROM book where libraryId = ?";
    public static final String ADD_NEW_BOOK = "INSERT INTO book(id, title, subject, language, " +
            "nrOfPages, price, nrOfCopies, categoryId, publishingHouseId, libraryId) " +
            "values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_BOOK_ID = "SELECT id FROM book WHERE title = ? and subject = ? " +
            "and language = ? and nrOfPages = ? and price = ? and categoryId = ? and " +
            "publishingHouseId = ?";
    public static final String GET_ALL_BOOKS = "SELECT * FROM book";
    public static final String GET_TITLES_FOR_CATEGORY = "SELECT title FROM book b join category c on (b.categoryId = c.id) WHERE c.name = ?";
    public static final String DELETE_BOOK = "DELETE FROM book WHERE id = ?";
    public static final String INSERT_ASSOCIATIVE_TABLE = "INSERT INTO book_author (bookId, authorId) VALUES (?, ?)";

    public static final String GET_NR_OF_EXEMPLARS_FOR_TITLE = "SELECT COUNT(*) from book_item bi " +
            "JOIN book b on (b.id = bi.bookId) WHERE b.title = ?";
    public static final String GET_AVAILABLE_TITLES = "SELECT title from book b join book_item bi ON (b.id = bi.bookId) WHERE status = ?";
    public static final String GET_VALUE_OF_PURCHASED_BOOKS = "SELECT price FROM book b JOIN book_item bi on (bi.bookId = b.id) WHERE status = ?";
    public static final String ADD_BOOK_ITEM = "INSERT INTO book_item values(null, ?, ?, ?)";
    public static final String GET_ITEM_BY_ID = "SELECT * FROM book_item WHERE id = ?";
    public static final String UPDATE_ITEM_STATUS = "UPDATE book_item SET status = ? WHERE id = ?";
    public static final String UPDATE_ITEM_DATE = "UPDATE book_item SET dateOfPurchase = ? WHERE id = ?";


    public static final String ADD_MEMBER = "INSERT INTO member VALUES (null, ?, ?, ?, ?, ?)";
    public static final String GET_MEMBER_ID = "SELECT id FROM member WHERE firstName = ? and lastName = ?" +
            "and phoneNumber = ? and membershipDate = ?";
    public static final String CLOSE_MEMBER_ACCOUNT = "UPDATE member SET status = ? WHERE id = ?";
    public static final String VERIFY_MEMBER_ID = "SELECT * FROM member WHERE id = ?";
    public static final String GET_ALL_MEMBERS = "SELECT * FROM member";
    public static final String DELETE_MEMBER = "DELETE FROM member WHERE id = ?";

    public static final String ADD_RESERVATION = "INSERT INTO book_reservation values (null, ?, ?, ?)";
    public static final String GET_RESERVATION_BY_ID = "SELECT * FROM book_reservation WHERE id = ?";
    public static final String UPDATE_RESERVATION_STATUS = "UPDATE book_reservation SET status = ? WHERE id = ?";

    public static final String ADD_BORROWING = "INSERT INTO book_borrowing values (null, ?, ?, ?, ?)";
}
