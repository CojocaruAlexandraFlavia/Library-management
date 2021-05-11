package Service;

import Classes.*;
import Enums.BookStatus;
import Enums.MemberStatus;
import Enums.ReservationStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileReaderService {

    private FileReaderService(){}

    private  static class SINGLETON_HOLDER{
        private static final FileReaderService INSTANCE = new FileReaderService();
    }

    public static FileReaderService getInstance(){
        return FileReaderService.SINGLETON_HOLDER.INSTANCE;
    }

    private final Map<Integer, Address> addresses = new HashMap<>();
    private final Map<Integer, Member> members = new HashMap<>();
    private final Map<Integer, PublishingHouse> publishingHouses = new HashMap<>();
    private final Map<Integer, Category> categories = new HashMap<>();
    private final Map<Integer, Author> authors = new HashMap<>();
    private final Map<Integer, Librarian> librarians = new HashMap<>();
    private final Map<Integer, Book> books = new HashMap<>();
    private final Map<Integer, BookItem> bookItems = new HashMap<>();
    private final Map<Integer, Library> libraries = new HashMap<>();
    private final Map<Integer, BookBorrowing> borrowings = new HashMap<>();
    private final Map<Integer, BookReservation> reservations = new HashMap<>();

    public void readAllAddresses(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Addresses.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Address address = new Address(values[1], values[2], values[3], values[4]);
            addresses.put(Integer.parseInt(values[0]), address);
        }
    }

    public void readAllPublishingHouses(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\PublishingHouses.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            PublishingHouse publishingHouse = new PublishingHouse(values[1], values[2]);
            publishingHouses.put(Integer.parseInt(values[0]), publishingHouse);
        }
    }


    public void readAllPersons(){
        String path = "C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\";
        List<String> fileNames = new ArrayList<>();
        fileNames.add("Authors.csv");
        fileNames.add("Librarians.csv");
        fileNames.add("Members.csv");
        for(String fileName : fileNames){
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(path + fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert scanner != null;
            String header = scanner.nextLine();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                switch (fileName) {
                    case "Authors.csv" -> {
                        Author author = new Author(values[1], values[2], values[3]);
                        authors.put(Integer.parseInt(values[0]), author);
                    }
                    case "Librarians.csv" -> {
                        Date hiringDate = null;
                        try {
                            hiringDate = new SimpleDateFormat("dd-MM-yyyy").parse(values[4]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Librarian librarian = new Librarian(values[1], values[2], values[3], hiringDate);
                        librarians.put(Integer.parseInt(values[0]), librarian);
                    }
                    case "Members.csv" -> {
                        Date membershipDate = null;
                        try {
                            membershipDate = new SimpleDateFormat("dd-MM-yyyy").parse(values[4]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Member member = new Member(values[1], values[2], values[3], Integer.parseInt(values[0]), membershipDate, Enum.valueOf(MemberStatus.class, values[5]));
                        members.put(Integer.parseInt(values[0]), member);
                    }
                }
            }

        }
    }

    public void readAllCategories(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Categories.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Category category = new Category(values[1]);
            categories.put(Integer.parseInt(values[0]), category);
        }
    }

    public AbstractMap.SimpleEntry<Integer, Book> readBook(Scanner scanner){
        String line = scanner.nextLine();
        String[] values = line.split(",");
        Category category = categories.get(Integer.parseInt(values[6]));
        PublishingHouse publishingHouse = publishingHouses.get(Integer.parseInt(values[5]));
        List<Author> bookAuthors = new ArrayList<>();
        String[] authorsIds = values[7].split(" ");
        for(String id : authorsIds){
            Author author = authors.get(Integer.parseInt(id));
            bookAuthors.add(author);
        }
        Book book = new Book(values[1], values[2], values[3], Integer.parseInt(values[4]), publishingHouse, bookAuthors,
                category, Double.parseDouble(values[8]), Integer.parseInt(values[9]));
        return new AbstractMap.SimpleEntry<>(Integer.parseInt(values[0]), book);
    }

    public void readAllBooks(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Books.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while (scanner.hasNextLine()){
            Map.Entry<Integer, Book> pair = readBook(scanner);
            books.put(pair.getKey(), pair.getValue());
        }
    }

    public void readAllBookItems(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\BookItems.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Book book = books.get(Integer.parseInt(values[1]));
            Date dateOfPurchase = null;
            if(!values[2].equals("null")){
                try {
                    dateOfPurchase = new SimpleDateFormat("dd-MM-yyyy").parse(values[2]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            BookItem item = new BookItem(book.getTitle(),book.getSubject(),book.getLanguage(),book.getNumberOfPages(),book.getPublishingHouse(),
                    book.getAuthors(),book.getCategory(), book.getPrice(), book.getNrOfCopies(), Integer.parseInt(values[0]),
                    dateOfPurchase, Enum.valueOf(BookStatus.class, values[3]));
            bookItems.put(Integer.parseInt(values[0]), item);
        }
    }

    public void readAllLibraries(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Libraries.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Address address = addresses.get(Integer.parseInt(values[2]));
            List<Librarian> librariansHere = new ArrayList<>();
            String[] librariansIds = values[3].split(" ");
            for(String id : librariansIds){
                Librarian librarian = librarians.get(Integer.parseInt(id));
                librariansHere.add(librarian);
            }
            List<Book> libraryBooks = new ArrayList<>();
            String[] booksIds = values[4].split(" ");
            for(String id : booksIds){
                Book book = books.get(Integer.parseInt(id));
                libraryBooks.add(book);
            }
            Library library = new Library(values[1], address, librariansHere, libraryBooks);
            libraries.put(Integer.parseInt(values[0]), library);
        }
    }

    public void readAllReservations(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Reservations.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            BookItem item = bookItems.get(Integer.parseInt(values[3]));
            BookReservation reservation = new BookReservation(Integer.parseInt(values[0]), Integer.parseInt(values[1]), item, Enum.valueOf(ReservationStatus.class, values[2]));
            reservations.put(Integer.parseInt(values[0]), reservation);
        }
    }

    public void readAllBorrowings(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Borrowings.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Date borrowingDate = null, dueDate = null;
            try {
                borrowingDate = new SimpleDateFormat("dd-MM-yyyy").parse(values[3]);
                dueDate = new SimpleDateFormat("dd-MM-yyyy").parse(values[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            BookBorrowing borrowing = new BookBorrowing(Integer.parseInt(values[1]), dueDate, borrowingDate, Integer.parseInt(values[4]));
            borrowings.put(Integer.parseInt(values[0]), borrowing);
        }
    }

    public void readAllFiles(){
        readAllAddresses();
        readAllCategories();
        readAllPublishingHouses();
        //readAllAuthors();
        readAllBooks();
        //readAllLibrarians();
        //readAllMembers();
        readAllPersons();
        readAllBookItems();
        readAllBorrowings();
        readAllReservations();
        readAllLibraries();
    }

    public Map<Integer, Address> getAddresses() {
        return addresses;
    }

    public Map<Integer, Member> getMembers() {
        return members;
    }

    public Map<Integer, PublishingHouse> getPublishingHouses() {
        return publishingHouses;
    }

    public Map<Integer, Category> getCategories() {
        return categories;
    }

    public Map<Integer, Author> getAuthors() {
        return authors;
    }

    public Map<Integer, Librarian> getLibrarians() {
        return librarians;
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public Map<Integer, BookItem> getBookItems() {
        return bookItems;
    }

    public Map<Integer, Library> getLibraries() {
        return libraries;
    }

    public Map<Integer, BookBorrowing> getBorrowings() {
        return borrowings;
    }

    public Map<Integer, BookReservation> getReservations() {
        return reservations;
    }
}
