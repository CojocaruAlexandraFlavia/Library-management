package repository.actions;

public interface BookAuthorRepository {
    void insertAssociativeTable(int bookId, int authorId);
}
