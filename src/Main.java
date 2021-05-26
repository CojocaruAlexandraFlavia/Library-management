import enums.BookStatus;
import enums.MemberStatus;
import enums.ReservationStatus;
import models.*;
import service.MainService;
import service.databaseActions.DbDeleteService;
import service.databaseActions.DbInsertService;
import service.databaseActions.DbSelectService;
import service.databaseActions.DbUpdateService;
import service.filesReaderWriter.AuditReportGeneratorService;

import java.util.*;

public class Main {
    public static void showMenu(){
        System.out.println("Choose one option:");
        System.out.println("1. Verify your account state.");
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
        System.out.println("17. Delete one librarian.");
        System.out.println("18. Stop the program");
    }

    public static void main(String[] args) {

        AuditReportGeneratorService auditReportGeneratorService = AuditReportGeneratorService.getInstance();
        String reportPath = auditReportGeneratorService.generateAuditReport();

       /**
        FileReaderService fileReaderService = FileReaderService.getInstance();
        fileReaderService.readAllFiles();
        FileWriterService fileWriterService = FileWriterService.getInstance();
        */

        MainService mainService = MainService.getInstance();
        DbSelectService dbSelectService = DbSelectService.getInstance();
        DbDeleteService dbDeleteService = DbDeleteService.getInstance();
        DbInsertService dbInsertService = DbInsertService.getInstance();
        DbUpdateService dbUpdateService = DbUpdateService.getInstance();
        Scanner scanner = new Scanner (System.in);

        while(true){
            showMenu();
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 -> {
                    System.out.println("Write your member id.");
                    int id = Integer.parseInt(scanner.nextLine());
                    Member member = dbSelectService.verifyMemberId(id);
                    if (member.getId() == -1) {
                        System.out.println("You are not a member. You can create an account if you want by choosing number 5 next time.");
                    } else if (member.getStatus().equals(MemberStatus.CLOSED)){
                        System.out.println("Your account is closed");
                    }else{
                        System.out.println("Your account is active.");
                    }
                    auditReportGeneratorService.addActionToReport(1, reportPath);
                }
                case 2 -> {
                    Book book = mainService.readBookData(scanner);
                    if(!book.getTitle().equals("")){
                        dbInsertService.addBook(book, java.util.Optional.empty());
                        auditReportGeneratorService.addActionToReport(2, reportPath);
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
                        //mainService.addLibrarian(librarian);
                    }
                    System.out.println("How many books the library will have at the beginning?");
                    int numberOfBooks = Integer.parseInt(scanner.nextLine());
                    List<Book> books = new ArrayList<>();
                    for (int i = 0; i < numberOfBooks; i++) {
                        Book book = mainService.readBookData(scanner);
                        books.add(book);
                    }
                    Address address = mainService.readAddressData(scanner);
                    Library library = new Library(name, address, librarians, books);
                    dbInsertService.addNewLibrary(library);
                    auditReportGeneratorService.addActionToReport(3, reportPath);
                }
                case 4 -> {
                    System.out.println("Write the category name");
                    String categoryName = scanner.nextLine();
                    Category category = new Category(categoryName);
                    //mainService.addCategory(category);
                    int result = dbInsertService.addCategory(category);
                    if(result == -1){
                        System.out.println("Something went wrong");
                    }
                    else{
                        System.out.println("Category added");
                        auditReportGeneratorService.addActionToReport(4, reportPath);
                    }
                }
                case 5 -> {
                    Member member = (Member)mainService.readPersonData(scanner, 1);
                    dbInsertService.addNewMember(member);
                    auditReportGeneratorService.addActionToReport(5, reportPath);
                }
                case 6 -> {
                    System.out.println("Write the name.");
                    String phName = scanner.nextLine();
                    System.out.println("Write the description.");
                    String description = scanner.nextLine();
                    PublishingHouse publishingHouse1 = new PublishingHouse(phName, description);
                    dbInsertService.addPublishingHouse(publishingHouse1);
                    auditReportGeneratorService.addActionToReport(6, reportPath);
                }
                case 7 -> {
                    System.out.println("Write the book title.");
                    String title = scanner.nextLine();
                    int nrOfExemplars = dbSelectService.getNumberOfExemplarsForATitle(title);
                    if(nrOfExemplars == 0){
                        System.out.println("Title not available.");
                    }
                    else{
                        System.out.println(nrOfExemplars + " exemplars");
                        auditReportGeneratorService.addActionToReport(7, reportPath);
                    }
                }
                case 8 -> {
                    Author author = (Author)mainService.readPersonData(scanner,3);
                    int result = dbInsertService.addAuthor(author);
                    if(result == -1){
                        System.out.println("The author already exists");
                    }
                    else{
                        System.out.println("Author added");
                        auditReportGeneratorService.addActionToReport(8, reportPath);
                    }
                }
                case 9 -> {
                    System.out.println("Write your member id.");
                    int memberID = Integer.parseInt(scanner.nextLine());
                    Member member = dbSelectService.verifyMemberId(memberID);
                    if (member.getId() > 0 && member.getStatus().equals(MemberStatus.ACTIVE)) {
                        System.out.println("Write the book item id that you want to reserve.");
                        int bookItemId = Integer.parseInt(scanner.nextLine());
                        BookItem foundItem = dbSelectService.getItemById(bookItemId);
                        if (foundItem.getItemId() == -1) {
                            System.out.println("The book item id is wrong. Try again.");
                        } else if (foundItem.getBookStatus() != BookStatus.AVAILABLE) {
                            System.out.println("The book item is not available.");
                        } else {
                            BookReservation reservation = new BookReservation(-1, memberID, foundItem, ReservationStatus.WAITING);
                            dbInsertService.addReservation(reservation);
                            auditReportGeneratorService.addActionToReport(9, reportPath);
                        }
                    } else {
                        System.out.println("You can't make a reservation without being a member or if your account is closed");
                    }
                }
                case 10 -> {
                    System.out.println("Write your member id.");
                    int ID = Integer.parseInt(scanner.nextLine());
                    Member member = dbSelectService.verifyMemberId(ID);
                    if (member.getId() > 0 && member.getStatus().equals(MemberStatus.ACTIVE)) {
                        System.out.println("Write the book item id that you want to borrow.");
                        int bookItemId = Integer.parseInt(scanner.nextLine());
                        BookItem bookItem = dbSelectService.getItemById(bookItemId);
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
                            dbInsertService.addBorrowing(borrowing);
                            auditReportGeneratorService.addActionToReport(10, reportPath);
                        }
                    } else {
                        System.out.println("You can't borrow a book if you are not a member or if your account is closed.");
                    }
                }
                case 11 -> {
                    System.out.println("You can't buy a book without a reservation. Write your reservation id.");
                    int reservationId = Integer.parseInt(scanner.nextLine());
                    BookReservation reservation = dbSelectService.searchReservationById(reservationId);
                    if (reservation.getReservationId() == -1) {
                        System.out.println("Sorry, it seems that you don't have a reservation.");
                    } else {
                        BookItem item = reservation.getBookItem();
                        dbUpdateService.buyAReservedItem(reservation);
                        System.out.println("You bought the book " + item.getTitle());
                        auditReportGeneratorService.addActionToReport(11, reportPath);
                    }
                }
                case 12 -> {
                    List<Book> books = dbSelectService.retrieveAllBooksSortedByTitle();
                    for(Book book: books){
                        System.out.println(book);
                    }
                    auditReportGeneratorService.addActionToReport(12, reportPath);
                }
                case 13 -> {
                    Set<String> availableBooks = dbSelectService.getAvailableTitles();
                    for(String title : availableBooks){
                        System.out.println(title);
                    }
                    auditReportGeneratorService.addActionToReport(13, reportPath);
                }
                case 14 -> {
                    System.out.println("Write the category name");
                    String categoryName = scanner.nextLine();
                    List<String> foundBooks = dbSelectService.getTitlesForCategory(categoryName);
                    if (foundBooks.size() == 0){
                        System.out.println("There is not any book from this category.");
                    }
                    else {
                        for (String title: foundBooks) {
                            System.out.println(title);
                        }
                        auditReportGeneratorService.addActionToReport(14, reportPath);
                    }
                }
                case 15 -> {
                    System.out.println(dbSelectService.valueOfPurchasedBooks());
                    auditReportGeneratorService.addActionToReport(15, reportPath);
                }
                case 16 -> {
                    System.out.println("Write your member id.");
                    int memberId = Integer.parseInt(scanner.nextLine());
                    if (!dbSelectService.verifyMemberId(memberId).getFirstName().equals("")) {
                        dbUpdateService.closeMemberAccount(memberId);
                        auditReportGeneratorService.addActionToReport(17, reportPath);
                    } else {
                        System.out.println("Wrong member id. Try to write it correctly next time.");
                    }
                }
                case 17 -> {
                    System.out.println("Write the librarian's id that you want to remove from the database");
                    int id = Integer.parseInt(scanner.nextLine());
                    Librarian librarian = dbSelectService.getLibrarianById(id);
                    if(librarian.getFirstName().equals("") && librarian.getLastName().equals("")){
                        System.out.println("The id is wrong. Try again.");
                    }
                    else{
                        dbDeleteService.deleteLibrarian(id);
                        auditReportGeneratorService.addActionToReport(17, reportPath);
                    }
                }
                case 18 -> {
                    System.out.println("Stopping system..");
                    System.exit(0);
                }
                default -> System.out.println("Write a correct option.");
            }
        }
    }
}
