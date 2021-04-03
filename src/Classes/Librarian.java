package Classes;

import java.util.Date;

public class Librarian extends Person{
    private Date hiringDate;
    private Library library;

    public Librarian(String firstName, String lastName, Address address, String phoneNumber, Date hiringDate, Library library) {
        super(firstName, lastName, address, phoneNumber);
        this.hiringDate = hiringDate;
        this.library = library;
    }

    public Librarian(){
        super();
        this.hiringDate = new Date();
        this.library = new Library();
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
