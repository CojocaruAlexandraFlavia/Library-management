package Classes;

import java.util.Date;

public class Librarian extends Person{
    private Date hiringDate;

    public Librarian(String firstName, String lastName, Address address, String phoneNumber, Date hiringDate) {
        super(firstName, lastName, address, phoneNumber);
        this.hiringDate = hiringDate;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "hiringDate=" + hiringDate +
                '}';
    }


}
