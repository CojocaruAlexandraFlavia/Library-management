package Classes;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
        //this.books = books;
    }

    public Category(){
        this.name = "";
        //this.books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public List<Book> getBooks() {
    //    return books;
    //}

    //public void setBooks(List<Book> books) {
    //    this.books = books;
    //}
}
