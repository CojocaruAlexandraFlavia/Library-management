package models;

import enums.MemberStatus;

import java.util.Date;

public class Librarian extends Person{
    private Date hiringDate;

    public Librarian(String firstName, String lastName, String phoneNumber, Date hiringDate){//}, Library library) {
        super(firstName, lastName, phoneNumber);
        this.hiringDate = hiringDate;
    }

    public Librarian(){
        super();
        this.hiringDate = new Date();
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

    public void blockMember(Member member){
        member.setStatus(MemberStatus.BLOCKED);
    }
}
