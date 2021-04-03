package Classes;

import Enums.MemberStatus;
import java.util.Date;

public class Member extends Person {
    private Date membershipDate;
    private int totalBooksCheckouts;
    private MemberStatus status;

    public Member(String firstName, String lastName, Address address, String phoneNumber, Date membershipDate, MemberStatus status) {
        super(firstName, lastName, address, phoneNumber);
        this.membershipDate = membershipDate;
        this.status = status;
    }

    public Member(){
        super();
        this.membershipDate = new Date();
        this.status = MemberStatus.ACTIVE;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }

    public int getTotalBooksCheckouts() {
        return totalBooksCheckouts;
    }

    public void setTotalBooksCheckouts(int totalBooksCheckouts) {
        this.totalBooksCheckouts = totalBooksCheckouts;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }
}
