package repository.actions;

import models.Member;

import java.util.List;

public interface MemberRepository {

    void addNewMember(Member member);
    int closeMemberAccount(int memberId);
    List<Member> retrieveAllMembers();
    int updateMember(Member member);
    int getMemberId(Member member);
    Member verifyMemberId(int id);
    void deleteMember(int id);
}
