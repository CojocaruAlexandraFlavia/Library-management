package Classes;

import java.util.Date;

public class BookBorrowing {
    private int memberId;
    private Date dueDate;
    private Date returnDate;
    private int itemId;

    public BookBorrowing(int memberId, Date dueDate, Date returnDate, int itemId) {
        this.memberId = memberId;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
