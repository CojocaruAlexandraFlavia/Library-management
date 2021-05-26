package repository.actionsImplementation;

import enums.MemberStatus;
import models.Member;
import repository.actions.MemberRepository;
import utils.DBConnection;

import static utils.Queries.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MemberRepositoryImpl implements MemberRepository {

    private MemberRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final MemberRepositoryImpl INSTANCE = new MemberRepositoryImpl();
    }

    public static MemberRepositoryImpl getInstance(){
        return MemberRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }

    private final DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public void addNewMember(Member member) {
        try{
            PreparedStatement preparedStatement = completeStatement(member, ADD_MEMBER);
            preparedStatement.setString(5, member.getStatus().toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int closeMemberAccount(int memberId) {
        if(verifyMemberId(memberId).getFirstName().equals("")){
            return -1;
        }
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(CLOSE_MEMBER_ACCOUNT);
            preparedStatement.setString(1, String.valueOf(MemberStatus.CLOSED));
            preparedStatement.setInt(2, memberId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public List<Member> retrieveAllMembers() {
        List<Member> members = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_ALL_MEMBERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                members.add(new Member(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"), resultSet.getDate("membershipDate"), MemberStatus.valueOf(resultSet.getString("status"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public int updateMember(Member member) {
        return 0;
    }

    @Override
    public int getMemberId(Member member) {
        try{
            PreparedStatement preparedStatement = completeStatement(member, GET_MEMBER_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Integer.parseInt(resultSet.getString(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Member verifyMemberId(int id) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(VERIFY_MEMBER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Member(Integer.parseInt(resultSet.getString("id")), resultSet.getDate("membershipDate"), MemberStatus.valueOf(resultSet.getString("status")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Member();
    }

    @Override
    public void deleteMember(int id) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_MEMBER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private PreparedStatement completeStatement(Member member, String getMemberId) throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(getMemberId);
        preparedStatement.setString(1, member.getFirstName());
        preparedStatement.setString(2, member.getLastName());
        preparedStatement.setString(3, member.getPhoneNumber());
        preparedStatement.setDate(4, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(member.getMembershipDate())));
        return preparedStatement;
    }
}
