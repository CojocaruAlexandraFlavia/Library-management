package Service;

import Classes.*;
import Interfaces.Search;

import java.util.*;

public class MainService implements Search {
    Set<Book> books;
    Set<Author> authors;
    Set<Library> libraries;
    Set<Member> members;
    Set<Librarian> librarians;
    Set<PublishingHouse> publishingHouses;
    Set<Category> categories;
    Set<Address> addresses;
    Set<BookItem> bookItems;


    public void addAddress(Address address){
        addresses.add(address);
        System.out.println("The address " + address.getStreet() + ", " + address.getCity() + ", " + address.getCountry() + ", " + address.getZipcode() + " was added.\n");
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

    public List<Book> searchBookByAuthor(String firstName, String lastName){
        List<Book> booksFound = new ArrayList<>();
        for(Author author : authors) {
            if(author.getLastName().equals(lastName) && author.getFirstName().equals(firstName)){
                return author.getBooks();
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



}
