package Classes;

import java.util.List;

public class Book {
    private String title;
    private String subject;
    private String language;
    private int numberOfPages;
    private Publisher publisher;
    private List<Author> authors;

    public Book(String title, String subject, String language, int numberOfPages, Publisher publisher, List<Author> authors) {
        this.title = title;
        this.subject = subject;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
