package Classes;

import java.util.Date;

public class BookLending {
    private int memberId;
    private Date dueDate;
    private Date returnDate;

    public BookLending(int memberId, Date dueDate, Date returnDate) {
        this.memberId = memberId;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
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
