package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Author extends Person {
    //private List<Book> books;

    public Author(String firstName, String lastName, String phoneNumber){//, List<Book> books) {
        super(firstName, lastName, phoneNumber);
        //this.books = books;
    }

    public Author(){
        super();
    }


     @Override
     public String toString() {
     return "Author{" +
     "firstName='" + getFirstName() + '\'' +
     ", lastName='" + getLastName() + '\'' +
     '}';
     }

}
