package Classes;

import Enums.MemberStatus;
import java.util.Date;

public class Member extends Person {
    private int id;
    private Date membershipDate;
    private MemberStatus status;

    public Member(String firstName, String lastName, String phoneNumber, int id, Date membershipDate, MemberStatus status) {
        super(firstName, lastName, phoneNumber);
        this.id = id;
        this.membershipDate = membershipDate;
        this.status = status;
    }

    public Member(){
        super();
        this.id = 0;
        this.membershipDate = new Date();
        this.status = MemberStatus.ACTIVE;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
