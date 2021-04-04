package Service;

import Classes.*;
import Enums.BookStatus;
import Enums.MemberStatus;
import Interfaces.Search;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainService implements Search {
    TreeSet<Book> books = new TreeSet<>();
    Set<Author> authors = new HashSet<>();
    TreeSet<Library> libraries = new TreeSet<>();
    Set<Member> members = new HashSet<>();
    Set<Librarian> librarians = new HashSet<>();
    Set<PublishingHouse> publishingHouses = new HashSet<>();
    Set<Category> categories = new HashSet<>();
    Set<Address> addresses = new HashSet<>();
    Set<BookItem> bookItems = new HashSet<>();
    Set<BookReservation> reservations = new HashSet<>();
    Set<BookBorrowing> borrowings = new HashSet<>();
    double totalSales = 0.0;
    int itemIds = 0;
    AtomicInteger memberIds = new AtomicInteger();

    public void addAddress(Address address){
        addresses.add(address);
        System.out.println("The address " + address.getStreet() + ", " + address.getCity() + ", " + address.getCountry() + ", " + address.getZipCode() + " was added.\n");
    }

    public void addBook(Book book){
        books.add(book);
        for(int i = 0; i < 10; i++){
            BookItem item = new BookItem(book.getTitle(), book.getSubject(), book.getLanguage(), book.getNumberOfPages(),
                    book.getPublishingHouse(), book.getAuthors(), book.getCategory(), book.getPrice(), itemIds++, new Date(), BookStatus.AVAILABLE);
            bookItems.add(item);
        }
        System.out.println("The book " + book.getTitle() + " was added.\n");
    }

    public void addAuthor(Author author){
        authors.add(author);
        System.out.println("The author " + author.getFirstName() + " " + author.getLastName() + " was added.\n");
    }

    public void addCategory(Category category){
        categories.add(category);
        System.out.println("The category " + category.getName() + " was added.\n");
    }

    public void addLibrary(Library library){
        libraries.add(library);
        System.out.println("The library " + library.getName() + " was added.\n");
    }

    public void addPublishingHouse(PublishingHouse publishingHouse){
        publishingHouses.add(publishingHouse);
        System.out.println("The publishing house " + publishingHouse.getName() + " was added.\n");
    }

    public void addMember(Member member){
        members.add(member);
        System.out.println("The member " + member.getFirstName() + " " + member.getLastName() + " with the member id " + member.getId() + " was added.\n");
    }

    public void addLibrarian(Librarian librarian){
        librarians.add(librarian);
        System.out.println("The librarian " + librarian.getFirstName() + " " + librarian.getLastName() + " was added.\n");
    }

    public void addBookReservation(BookReservation reservation){
        reservations.add(reservation);
        System.out.println("The reservation for member " + reservation.getMemberId() + " was added.\n" );
    }

    public void addBookBorrowing(BookBorrowing bookBorrowing){
        borrowings.add(bookBorrowing);
        System.out.println("The book item " + bookBorrowing.getItemId() + " was borrowed by " + bookBorrowing.getMemberId() + "\n" );
    }

    public Book searchBookByTitle(String title){
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }

        return new Book();
    }

    public Set<Book> searchBookByAuthor(String firstName, String lastName){
        Set<Book> booksFound = new HashSet<>();
        for(Book book : books) {
            for(Author author : book.getAuthors()){
                if(author.getFirstName().equalsIgnoreCase(firstName) && author.getLastName().equalsIgnoreCase(lastName)){
                    booksFound.add(book);
                }
            }
        }
        return booksFound;
    }

    public Set<Book> searchBookByCategory(String categoryName){
        Set<Book> booksFound = new HashSet<>(Collections.emptySet());
        for(Book book : books){
            if(book.getCategory().getName().equalsIgnoreCase(categoryName)){
                booksFound.add(book);
            }
        }
        return booksFound;
    }

    public void showBookTitles(){
        for(Book book : books){
            System.out.println(book.getTitle());
        }
    }

    public void closeMemberAccount(int id){
        boolean memberFound = false;
        for(Member member : members){
            if (member.getId() == id){
                member.setStatus(MemberStatus.CLOSED);
                memberFound = true;
            }
        }
        if(!memberFound){
            System.out.println("Write the correct member id and try again.\n");
        }
        else{
            System.out.println("Your account is closed.\n");
        }
    }

    public void showTotalSales(){
        System.out.println("Total sales: " + totalSales + "\n");
    }

    public boolean verifyMember(int id){
        for(Member member : members){
            if(member.getId() == id){
                return true;
            }
        }
        return false;
    }

    public PublishingHouse publishingHouseSearch(String name){
        for(PublishingHouse publishingHouse: publishingHouses){
            if(publishingHouse.getName().equals(name)){
                return publishingHouse;
            }
        }
        return new PublishingHouse();
    }

    public Category categorySearch(String name){
        for(Category category : categories){
            if(category.getName().equalsIgnoreCase(name)){
                return category;
            }
        }
        return new Category();
    }

    public Author authorSearch(String firstName, String lastName){
        for(Author author : authors){
            if(author.getFirstName().equalsIgnoreCase(firstName) && author.getLastName().equalsIgnoreCase(lastName)){
                return author;
            }
        }
        return new Author();
    }

    public BookItem searchBookItemById(int id){
        for(BookItem bookItem : bookItems){
            if(bookItem.getItemId() == id){
                return bookItem;
            }
        }
        return new BookItem();
    }

    public void modifyBookItemStatus(int id, BookStatus status){
        for(BookItem bookItem : bookItems){
            if(bookItem.getItemId() == id){
                bookItem.setBookStatus(status);
                break;
            }
        }
    }

    public BookReservation searchReservationById(int id){
        for(BookReservation reservation : reservations){
            if(reservation.getReservationId() == id){
                return reservation;
            }
        }
        return new BookReservation();
    }

    public void addSale(double price){
        totalSales += price;
    }

    public void showAvailableTitles(){
        for(BookItem item : bookItems){
            if(item.getBookStatus().equals(BookStatus.AVAILABLE)){
                System.out.println(item.getTitle());
            }
        }
    }

    public Author readAuthorData(Scanner scanner){
        System.out.println("Does the author already exist in the database? YES or NO");
        String response = scanner.nextLine();
        Author author = new Author();
        if (response.equalsIgnoreCase("YES")) {
            System.out.println("Then write the first name.");
            String firstName = scanner.nextLine();
            System.out.println("Write the last name.");
            String lastName = scanner.nextLine();
            if (authorSearch(firstName, lastName).getFirstName().equals("")) {
                System.out.println("The first name or the last name is incorrect. You can try again later.");
            } else {
                author.setFirstName(authorSearch(firstName, lastName).getFirstName());
                author.setLastName(authorSearch(firstName, lastName).getLastName());
            }
        } else if (response.equalsIgnoreCase("NO")) {
            Author a = (Author) readPersonData(scanner, 3);
            addAuthor(a);
            return a;

        } else {
            System.out.println("Wrong answer.Try again later.");
        }
        return  author;
    }

    public Librarian readLibrarianData(Scanner scanner){
        System.out.println("Write the first name.");
        String firstName = scanner.nextLine();
        System.out.println("Write the last name.");
        String lastName = scanner.nextLine();
        System.out.println("Write the phone number.");
        String phoneNumber = scanner.nextLine();
        System.out.println("Write the hiring date like day-month-year");
        String[] hiringDate = scanner.nextLine().split("-");
        return new Librarian(firstName, lastName, phoneNumber, new Date(Integer.parseInt(hiringDate[0]), Integer.parseInt(hiringDate[1]), Integer.parseInt(hiringDate[2])));
    }

    public Book readBookData(Scanner scanner){
        System.out.println("Write the book title.");
        String bookTitle = scanner.nextLine();
        System.out.println("Write the book subject.");
        String bookSubject = scanner.nextLine();
        System.out.println("Write the language in which the book is written.");
        String bookLanguage = scanner.nextLine();
        System.out.println("Write the number of pages of the book.");
        int bookNumberOfPages = Integer.parseInt(scanner.nextLine());
        System.out.println("Does the publishing house already exist in the database? YES or NO");
        String response = scanner.nextLine();
        PublishingHouse publishingHouse = new PublishingHouse();
        if (response.equalsIgnoreCase("YES")) {
            System.out.println("Then write the publishing house name.");
            String name = scanner.nextLine();
            if (publishingHouseSearch(name).getName().equals("")) {
                System.out.println("The name is incorrect. You can try again later.");
                return new Book();
            } else {
                publishingHouse.setName(publishingHouseSearch(name).getName());
                publishingHouse.setDescription(publishingHouseSearch(name).getDescription());
            }
        } else if (response.equalsIgnoreCase("NO")) {
            System.out.println("Then write the publishing house name for adding it.");
            String pHName = scanner.nextLine();
            System.out.println("Now you can write the publishing house description.");
            String description = scanner.nextLine();
            publishingHouse.setName(pHName);
            publishingHouse.setDescription(description);
            addPublishingHouse(publishingHouse);
        } else {
            System.out.println("Wrong answer.Try again later.");
            return new Book();
        }

        System.out.println("Does the category already exist in the database? YES or NO");
        String response2 = scanner.nextLine();
        Category category = new Category();
        if (response2.equalsIgnoreCase("YES")) {
            System.out.println("Then write the category name.");
            String categoryName = scanner.nextLine();
            if (categorySearch(categoryName).getName().equals("")) {
                System.out.println("The name is incorrect. You can try again later.");
            } else {
                category.setName(publishingHouseSearch(categoryName).getName());
            }
        } else if (response2.equalsIgnoreCase("NO")) {
            System.out.println("Then write the category name for adding it.");
            String categoryName = scanner.nextLine();
            category.setName(categoryName);
            addCategory(category);
        } else {
            System.out.println("Wrong answer.Try again later.");
            return new Book();
        }
        List<Author> bookAuthors = new ArrayList<>();
        System.out.println("How many authors does the book have?");
        int bookNumberOfAuthors = Integer.parseInt(scanner.nextLine());
        for (int j = 0; j < bookNumberOfAuthors; j++) {
            Author author = readAuthorData(scanner);
            bookAuthors.add(author);
        }
        System.out.println("Write the price.");
        int bookPrice = Integer.parseInt(scanner.nextLine());
        return new Book(bookTitle, bookSubject, bookLanguage, bookNumberOfPages, publishingHouse, bookAuthors, category, bookPrice);
    }

    public Address readAddressData(Scanner scanner){
        System.out.println("Write the address: first, the street.");
        String addressStreet = scanner.nextLine();
        System.out.println("Now the city.");
        String addressCity = scanner.nextLine();
        System.out.println("The country..");
        String addressCountry = scanner.nextLine();
        System.out.println("Finally, the zip code.");
        String addressZipCode = scanner.nextLine();
        return new Address(addressStreet,addressCity,addressCountry,addressZipCode);
    }

    public Person readPersonData(Scanner scanner, int option){
        System.out.println("Write the first name.");
        String firstName = scanner.nextLine();
        System.out.println("Write the last name.");
        String lastName = scanner.nextLine();
        System.out.println("Write the phone number.");
        String phoneNumber = scanner.nextLine();
        if(option == 1){
            return new Member(firstName, lastName, phoneNumber, memberIds.getAndIncrement(), new Date(), MemberStatus.ACTIVE);
        }
        else if(option == 2){
            System.out.println("Write the hiring date like day-month-year");
            String[] hiringDate = scanner.nextLine().split("-");
            return new Librarian(firstName, lastName, phoneNumber, new Date(Integer.parseInt(hiringDate[0]), Integer.parseInt(hiringDate[1]), Integer.parseInt(hiringDate[2])));
        }
        else{
            return new Author(firstName, lastName, phoneNumber);
        }
    }
}

