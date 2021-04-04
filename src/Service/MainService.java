package Service;

import Classes.*;
import Enums.BookStatus;
import Enums.MemberStatus;
import Interfaces.Search;

import java.util.*;

public class MainService implements Search {
    TreeSet<Book> books = new TreeSet<>();
    Set<Author> authors = new HashSet<>();
    Set<Library> libraries = new HashSet<>();
    Set<Member> members = new HashSet<>();
    Set<Librarian> librarians = new HashSet<>();
    Set<PublishingHouse> publishingHouses = new HashSet<>();
    Set<Category> categories = new HashSet<>();
    Set<Address> addresses = new HashSet<>();
    Set<BookItem> bookItems = new HashSet<>();
    double totalSales = 0.0;


    public void addAddress(Address address){
        addresses.add(address);
        System.out.println("The address " + address.getStreet() + ", " + address.getCity() + ", " + address.getCountry() + ", " + address.getZipCode() + " was added.\n");
    }

    public void addBook(Book book){
        books.add(book);
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
        System.out.println("The member " + member.getFirstName() + " " + member.getLastName() + " was added.\n");
    }

    public void addLibrarian(Librarian librarian){
        librarians.add(librarian);
        System.out.println("The librarian " + librarian.getFirstName() + " " + librarian.getLastName() + " was added.\n");
    }


    public Book searchBookByTitle(String title){
        for(Book book : books){
            if(book.getTitle().equals(title)){
                return book;
            }
        }

        return (new Book("", "", "", 0, new PublishingHouse(), new ArrayList<>(), new Category()));
    }

    public Set<Book> searchBookByAuthor(String firstName, String lastName){
        Set<Book> booksFound = new HashSet<>();
        for(Book book : books) {
            for(Author author : book.getAuthors()){
                if(author.getFirstName().equals(firstName) && author.getLastName().equals(lastName)){
                    booksFound.add(book);
                }
            }
        }
        return booksFound;
    }

    public Set<Book> searchBookByCategory(String categoryName){
        Set<Book> booksFound = new HashSet<>(Collections.emptySet());
        for(Book book : books){
            if(book.getCategory().getName().equals(categoryName)){
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
    }

    public void buyBook(BookItem bookItem){
        bookItem.setBookStatus(BookStatus.BOUGHT);
        totalSales += bookItem.getPrice();
        bookItem.setDateOfPurchase(new Date());
    }

    public void showTotalSales(){
        System.out.println("Total sales: " + totalSales + "\n");
    }

    public void bookReservation(BookItem bookItem){
        bookItem.setBookStatus(BookStatus.RESERVED);
    }

    public boolean verifyMember(int id){
        for(Member member : members){
            if(member.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void bookLending(BookItem bookItem){
        bookItem.setBookStatus(BookStatus.BORROWED);
    }

    public PublishingHouse publishingHouseSearch(String name){
        for(PublishingHouse publishingHouse: publishingHouses){
            if(publishingHouse.getName().equals(name)){
                return publishingHouse;
            }
        }
        return (new PublishingHouse());
    }

    public Category categorySearch(String name){
        Category categoryNotFound = new Category();
        for(Category category : categories){
            if(category.getName().equals(name)){
                return category;
            }
        }
        return categoryNotFound;
    }

    public Author authorSearch(String firstName, String lastName){
        Author authorNotFound = new Author();
        for(Author author : authors){
            if(author.getFirstName().equals(firstName) && author.getLastName().equals(lastName)){
                return author;
            }
        }
        return authorNotFound;
    }




}
