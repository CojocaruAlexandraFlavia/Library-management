package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String firstName, String lastName, String phoneNumber){//, List<Book> books) {
        super(firstName, lastName, phoneNumber);
        //this.books = books;
    }

    public Author(){
        super();
        //this.books = new ArrayList<>();
    }


/**
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

     @Override
     public String toString() {
     return "Author{" +
     "firstName='" + firstName + '\'' +
     ", lastName='" + lastName + '\'' +
     //", books=" + books +
     '}';
     }
    **/

}
