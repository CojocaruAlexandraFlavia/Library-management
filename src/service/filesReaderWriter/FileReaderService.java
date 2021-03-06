package service.filesReaderWriter;

import models.*;
import enums.BookStatus;
import enums.MemberStatus;
import enums.ReservationStatus;

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


    public void readAllAddresses(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\Addresses.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Address address = new Address(values[1], values[2], values[3], values[4]);
            FilesObjects.addresses.put(Integer.parseInt(values[0]), address);
        }
    }

    public void readAllPublishingHouses(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\PublishingHouses.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            PublishingHouse publishingHouse = new PublishingHouse(values[1], values[2]);
            FilesObjects.publishingHouses.put(Integer.parseInt(values[0]), publishingHouse);
        }
    }


    public void readAllPersons(){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\";
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
                        FilesObjects.authors.put(Integer.parseInt(values[0]), author);
                    }
                    case "Librarians.csv" -> {
                        Date hiringDate = null;
                        try {
                            hiringDate = new SimpleDateFormat("dd-MM-yyyy").parse(values[4]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Librarian librarian = new Librarian(values[1], values[2], values[3], hiringDate);
                        FilesObjects.librarians.put(Integer.parseInt(values[0]), librarian);
                    }
                    case "Members.csv" -> {
                        Date membershipDate = null;
                        try {
                            membershipDate = new SimpleDateFormat("dd-MM-yyyy").parse(values[4]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Member member = new Member(values[1], values[2], values[3], Integer.parseInt(values[0]), membershipDate, Enum.valueOf(MemberStatus.class, values[5]));
                        FilesObjects.members.put(Integer.parseInt(values[0]), member);
                    }
                }
            }

        }
    }

    public void readAllCategories(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\Categories.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Category category = new Category(values[1]);
            FilesObjects.categories.put(Integer.parseInt(values[0]), category);
        }
    }

    public AbstractMap.SimpleEntry<Integer, Book> readBook(Scanner scanner){
        String line = scanner.nextLine();
        String[] values = line.split(",");
        Category category = FilesObjects.categories.get(Integer.parseInt(values[6]));
        PublishingHouse publishingHouse = FilesObjects.publishingHouses.get(Integer.parseInt(values[5]));
        List<Author> bookAuthors = new ArrayList<>();
        String[] authorsIds = values[7].split(" ");
        for(String id : authorsIds){
            Author author = FilesObjects.authors.get(Integer.parseInt(id));
            bookAuthors.add(author);
        }
        Book book = new Book(values[1], values[2], values[3], Integer.parseInt(values[4]), publishingHouse, bookAuthors,
                category, Double.parseDouble(values[8]), Integer.parseInt(values[9]));
        return new AbstractMap.SimpleEntry<>(Integer.parseInt(values[0]), book);
    }

    public void readAllBooks(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\Books.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while (scanner.hasNextLine()){
            Map.Entry<Integer, Book> pair = readBook(scanner);
            FilesObjects.books.put(pair.getKey(), pair.getValue());
        }
    }

    public void readAllBookItems(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\BookItems.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Book book = FilesObjects.books.get(Integer.parseInt(values[1]));
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
            FilesObjects.bookItems.put(Integer.parseInt(values[0]), item);
        }
    }

    public void readAllLibraries(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\Libraries.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Address address = FilesObjects.addresses.get(Integer.parseInt(values[2]));
            List<Librarian> librariansHere = new ArrayList<>();
            String[] librariansIds = values[3].split(" ");
            for(String id : librariansIds){
                Librarian librarian = FilesObjects.librarians.get(Integer.parseInt(id));
                librariansHere.add(librarian);
            }
            List<Book> libraryBooks = new ArrayList<>();
            String[] booksIds = values[4].split(" ");
            for(String id : booksIds){
                Book book = FilesObjects.books.get(Integer.parseInt(id));
                libraryBooks.add(book);
            }
            Library library = new Library(values[1], address, librariansHere, libraryBooks);
            FilesObjects.libraries.put(Integer.parseInt(values[0]), library);
        }
    }

    public void readAllReservations(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\Reservations.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        String header = scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            BookItem item = FilesObjects.bookItems.get(Integer.parseInt(values[3]));
            BookReservation reservation = new BookReservation(Integer.parseInt(values[0]), Integer.parseInt(values[1]), item, Enum.valueOf(ReservationStatus.class, values[2]));
            FilesObjects.reservations.put(Integer.parseInt(values[0]), reservation);
        }
    }

    public void readAllBorrowings(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\IntelliJ projects\\Library-management\\src\\reports\\Borrowings.csv"));
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
            FilesObjects.borrowings.put(Integer.parseInt(values[0]), borrowing);
        }
    }

    public void readAllFiles(){
        readAllAddresses();
        readAllCategories();
        readAllPublishingHouses();
        readAllBooks();
        readAllPersons();
        readAllBookItems();
        readAllBorrowings();
        readAllReservations();
        readAllLibraries();
    }


}
