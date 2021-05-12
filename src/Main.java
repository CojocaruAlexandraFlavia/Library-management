import Classes.*;
import Enums.BookStatus;
import Enums.ReservationStatus;
import Service.FileReaderService;
import Service.FileWriterService;
import Service.MainService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void showMenu(){
        System.out.println("Choose one option:");
        System.out.println("1. Verify your member id.");
        System.out.println("2. Add a book.");
        System.out.println("3. Add a library.");
        System.out.println("4. Add a category for books.");
        System.out.println("5. Add a new member.");
        System.out.println("6. Add a new publishing house.");
        System.out.println("7. Show the number of exemplars from a specific title.");
        System.out.println("8. Add a new author.");
        System.out.println("9. Make a book reservation.");
        System.out.println("10. Borrow a book.");
        System.out.println("11. Buy a book.");
        System.out.println("12. Show all the book ordered by their titles.");
        System.out.println("13. Show all the available titles.");
        System.out.println("14. Show all the titles from a specific category.");
        System.out.println("15. Show the total value of the purchased books.");
        System.out.println("16. Close your member account.");
        System.out.println("17. Stop the program");
    }

    public static void main(String[] args) {
       AuditReportGeneratorService auditReportGeneratorService = AuditReportGeneratorService.getInstance();
       String reportPath = auditReportGeneratorService.generateAuditReport();
       try {
           Files.write(Paths.get("C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Service\\AuditReportFileName.txt"), reportPath.getBytes());
       } catch (IOException e) {
           e.printStackTrace();
       }
        FileReaderService fileReaderService = FileReaderService.getInstance();
        fileReaderService.readAllFiles();

        FileWriterService fileWriterService = FileWriterService.getInstance();

        MainService mainService = MainService.getInstance();

        Scanner scanner = new Scanner (System.in);
        AtomicInteger reservationIds = new AtomicInteger();
        while(true){
            showMenu();
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 -> {
                    System.out.println("Write your member id.");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (!mainService.verifyMember(id)) {
                        System.out.println("You are not a member. You can create an account if you want by choosing number 5 next time.");
                    } else {
                        System.out.println("It's ok, you are a member.");
                    }
                }
                case 2 -> {
                    Book book = mainService.readBookData(scanner);
                    if(!book.getTitle().equals("")){
                        mainService.addBook(book);
                    }
                }
                case 3 -> {
                    System.out.println("Write the library name.");
                    String name = scanner.nextLine();
                    System.out.println("How many librarians will work there?");
                    int librariansNumber = Integer.parseInt(scanner.nextLine());
                    List<Librarian> librarians = new ArrayList<>();
                    for (int i = 0; i < librariansNumber; i++) {
                        Librarian librarian = mainService.readLibrarianData(scanner);
                        librarians.add(librarian);
                        mainService.addLibrarian(librarian);
                    }
                    System.out.println("How many books the library will have at the beginning?");
                    int numberOfBooks = Integer.parseInt(scanner.nextLine());
                    List<Book> books = new ArrayList<>();
                    for (int i = 0; i < numberOfBooks; i++) {
                        Book book = mainService.readBookData(scanner);
                        books.add(book);
                        mainService.addBook(book);
                    }
                    Address address = mainService.readAddressData(scanner);
                    Library library = new Library(name, address, librarians, books);
                    mainService.addAddress(address);
                    mainService.addLibrary(library);
                }
                case 4 -> {
                    System.out.println("Write the category name");
                    String categoryName = scanner.nextLine();
                    Category category = new Category(categoryName);
                    mainService.addCategory(category);
                }
                case 5 -> {
                    Member member = (Member)mainService.readPersonData(scanner, 1);
                    mainService.addMember(member);
                }
                case 6 -> {
                    System.out.println("Write the name.");
                    String phName = scanner.nextLine();
                    System.out.println("Write the description.");
                    String description = scanner.nextLine();
                    PublishingHouse publishingHouse1 = new PublishingHouse(phName, description);
                    mainService.addPublishingHouse(publishingHouse1);
                }
                case 7 -> {
                    System.out.println("Write the book title.");
                    String title = scanner.nextLine();
                    int nrOfExemplars = mainService.searchBookByTitle(title);
                    if(nrOfExemplars == 0){
                        System.out.println("Title not available.");
                    }
                    else{
                        System.out.println(nrOfExemplars + " exemplars");
                    }
                }
                case 8 -> {
                    Author author = (Author)mainService.readPersonData(scanner,3);
                    mainService.addAuthor(author);
                }
                case 9 -> {
                    System.out.println("Write your member id.");
                    int memberID = Integer.parseInt(scanner.nextLine());
                    if (mainService.verifyMember(memberID)) {
                        System.out.println("Write the book item id that you want to reserve.");
                        int bookItemId = Integer.parseInt(scanner.nextLine());
                        BookItem bookItem = mainService.searchBookItemById(bookItemId);
                        if (bookItem.getItemId() == -1) {
                            System.out.println("The book item id is wrong. Try again.");
                        } else if (bookItem.getBookStatus() != BookStatus.AVAILABLE) {
                            System.out.println("The book item is not available.");
                        } else {
                            BookReservation reservation = new BookReservation(reservationIds.getAndIncrement(), memberID, bookItem, ReservationStatus.PENDING);
                            mainService.addBookReservation(reservation);
                            mainService.modifyBookItemStatus(bookItem.getItemId(), BookStatus.RESERVED);
                        }
                    } else {
                        System.out.println("You can't make a reservation without being a member.");
                    }
                }
                case 10 -> {
                    System.out.println("Write your member id.");
                    int ID = Integer.parseInt(scanner.nextLine());
                    if (mainService.verifyMember(ID)) {
                        System.out.println("Write the book item id that you want to borrow.");
                        int bookItemId = Integer.parseInt(scanner.nextLine());
                        BookItem bookItem = mainService.searchBookItemById(bookItemId);
                        if (bookItem.getItemId() == -1) {
                            System.out.println("The book item id is wrong. Try again.");
                        } else if (bookItem.getBookStatus() != BookStatus.AVAILABLE) {
                            System.out.println("The book item is not available.");
                        } else {
                            Date dueDate = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(dueDate);
                            calendar.add(Calendar.DATE, 7);
                            BookBorrowing borrowing = new BookBorrowing(ID, calendar.getTime(), new Date(), bookItem.getItemId());
                            mainService.addBookBorrowing(borrowing);
                            mainService.modifyBookItemStatus(bookItem.getItemId(), BookStatus.BORROWED);
                        }
                    } else {
                        System.out.println("You can't borrow a book if you are not a member.");
                    }
                }
                case 11 -> {
                    System.out.println("You can't buy a book without a reservation. Write your reservation id.");
                    int reservationId = Integer.parseInt(scanner.nextLine());
                    BookReservation reservation = mainService.searchReservationById(reservationId);
                    if (reservation.getReservationId() == -1) {
                        System.out.println("Sorry, it seems that you don't have a reservation.");
                    } else {
                        BookItem item = reservation.getBookItem();
                        mainService.modifyBookItemStatus(item.getItemId(), BookStatus.BOUGHT);
                        mainService.addSale(item.getPrice());
                        reservation.setStatus(ReservationStatus.COMPLETED);
                        System.out.println("You bought the book " + item.getTitle());
                        //auditReportGeneratorService.addActionToReport(11, reportPath);
                    }
                }
                case 12 -> mainService.showBookTitles();
                case 13 -> mainService.showAvailableTitles();
                case 14 -> {
                    System.out.println("Write the category name");
                    String categoryName = scanner.nextLine();
                    Set<Book> foundBooks = mainService.searchBookByCategory(categoryName);
                    if (foundBooks.size() == 0){
                        System.out.println("There is not any book from this category.");
                    }
                    else {
                        for (Book book: foundBooks) {
                            System.out.println(book.getTitle());
                        }
                    }
                }
                case 15 -> mainService.showTotalSales();
                case 16 -> {
                    System.out.println("Write your member id.");
                    int memberId = Integer.parseInt(scanner.nextLine());
                    if (mainService.verifyMember(memberId)) {
                        mainService.closeMemberAccount(memberId);
                    } else {
                        System.out.println("Wrong member id. Try to write it correctly next time.");
                    }
                }
                case 17 -> {
                    System.out.println("Stopping system..");
                    System.exit(0);
                }
                default -> System.out.println("Write a correct option.");
            }
        }
    }
}
