package models;

import enums.BookStatus;

import java.util.Date;
import java.util.List;

public class BookItem extends  Book{//{
    private int itemId;
    private Date dateOfPurchase;
    private BookStatus bookStatus;

    public BookItem(String title, String subject, String language, int numberOfPages, PublishingHouse publishingHouse,
                    List<Author> authors, Category category, double price, int nrOfCopies, int itemId, Date dateOfPurchase,  BookStatus bookStatus) {
        super(title, subject, language, numberOfPages, publishingHouse, authors, category, price, nrOfCopies);
        this.itemId = itemId;
        this.dateOfPurchase = dateOfPurchase;
        this.bookStatus = bookStatus;
   }

    public BookItem(){
        super();
        this.itemId = -1;
        this.dateOfPurchase = null;//new Date();
        this.bookStatus = BookStatus.AVAILABLE;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
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
                "itemId=" + itemId +
                ", dateOfPurchase=" + dateOfPurchase +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
