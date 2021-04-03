package Classes;

import Enums.BookStatus;

import java.util.Date;
import java.util.List;

public class BookItem extends Book{
    private Date dateOfPurchase;
    private double price;
    private Date borrowed;
    private Date dueDate;
    private BookStatus bookStatus;

    public BookItem(String title, String subject, String language, int numberOfPages, Publisher publisher, List<Author> authors, Date dateOfPurchase, double price, Date borrowed, Date dueDate, BookStatus bookStatus) {
        super(title, subject, language, numberOfPages, publisher, authors);
        this.dateOfPurchase = dateOfPurchase;
        this.price = price;
        this.borrowed = borrowed;
        this.dueDate = dueDate;
        this.bookStatus = bookStatus;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Date borrowed) {
        this.borrowed = borrowed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }


    @Override
    public String toString() {
        return "BookItem{" +
                "dateOfPurchase=" + dateOfPurchase +
                ", price=" + price +
                ", borrowed=" + borrowed +
                ", dueDate=" + dueDate +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
