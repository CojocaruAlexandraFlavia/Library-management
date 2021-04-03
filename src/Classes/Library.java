package Classes;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private Address address;
    private List<Librarian> librarians;
    private List<Book> books;

    public Library(String name, Address address, List<Librarian> librarians, List<Book> books) {
        this.name = name;
        this.address = address;
        this.librarians = librarians;
        this.books = books;
    }

    public Library(){
        this.name = "";
        this.address = new Address();
        this.librarians = new ArrayList<Librarian>();
        this.books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(List<Librarian> librarians) {
        this.librarians = librarians;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
