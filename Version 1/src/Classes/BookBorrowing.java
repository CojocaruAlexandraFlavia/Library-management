package Classes;

import java.util.Date;

public class BookBorrowing {
    private int memberId;
    private Date dueDate;
    private Date borrowingDay;
    private int itemId;

    public BookBorrowing(int memberId, Date dueDate, Date borrowingDay, int itemId) {
        this.memberId = memberId;
        this.dueDate = dueDate;
        this.borrowingDay = borrowingDay;
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

    public Date getBorrowingDay() {
        return borrowingDay;
    }

    public void setBorrowingDay(Date borrowingDay) {
        this.borrowingDay = borrowingDay;
    }
}
