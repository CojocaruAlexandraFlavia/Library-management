package repository.actionsImplementation;

import models.Category;
import repository.actions.CategoryRepository;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final DBConnection dbConnection = DBConnection.getInstance();

    private CategoryRepositoryImpl(){}

    private  static class SINGLETON_HOLDER{
        private static final CategoryRepositoryImpl INSTANCE = new CategoryRepositoryImpl();
    }

    public static CategoryRepositoryImpl getInstance(){
        return CategoryRepositoryImpl.SINGLETON_HOLDER.INSTANCE;
    }


    @Override
    public List<Category> retrieveAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(RETRIEVE_ALL_CATEGORIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  categories;
    }
    @Override
    public int addCategory(Category category) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(ADD_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return Integer.parseInt(resultSet.getString(1));
            }

        }catch (SQLException e){
            System.out.println("The category already exists");
        }

        return -1;
    }

    @Override
    public void deleteCategory(int categoryId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(DELETE_CATEGORY);
            preparedStatement.setString(1, String.valueOf(categoryId));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public Category getCategoryForBook(int bookId) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_CATEGORY_FOR_BOOK);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Category(resultSet.getString("name"));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return new Category("");
    }

    @Override
    public int getCategoryId(Category category) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_CATEGORY_ID);
            preparedStatement.setString(1, category.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Category getCategoryByName(String name) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(GET_CATEGORY_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Category(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Category("");
    }

    @Override
    public void updateCategory(int id, String name) {
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(UPDATE_CATEGORY);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
