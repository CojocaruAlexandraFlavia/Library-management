package repository.actions;

import models.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> retrieveAllCategories();
    int addCategory(Category category);
    void deleteCategory(int categoryId);
    Category getCategoryForBook(int bookId);
    int getCategoryId(Category category);
    Category getCategoryByName(String name);
    void updateCategory(int id, String name);
}
