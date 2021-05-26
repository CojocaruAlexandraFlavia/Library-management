package models;

import java.util.ArrayList;
import java.util.List;

public class Book implements Comparable<Book> {
    private String title;
    private String subject;
    private String language;
    private int numberOfPages;
    private double price;
    private int nrOfCopies;
    private Category category;
    private PublishingHouse publishingHouse;
    private List<Author> authors;

    public Book(String title, String subject, String language, int numberOfPages, PublishingHouse publishingHouse,
                List<Author> authors, Category category, double price, int nrOfCopies) {
        this.title = title;
        this.subject = subject;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.publishingHouse = publishingHouse;
        this.authors = authors;
        this.category = category;
        this.price = price;
        this.nrOfCopies = nrOfCopies;
    }

    public Book(){
        this.title = "";
        this.subject = "";
        this.language = "";
        this.numberOfPages = 0;
        this.publishingHouse = new PublishingHouse();
        this.authors = new ArrayList<>();
        this.category = new Category();
        this.price = 0.0;
        this.nrOfCopies = 0;
    }

    public int getNrOfCopies() {
        return nrOfCopies;
    }

    public void setNrOfCopies(int nrOfCopies) {
        this.nrOfCopies = nrOfCopies;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public PublishingHouse getPublisher() {
        return publishingHouse;
    }

    public void setPublisher(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", language='" + language + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", publishingHouse=" + publishingHouse +
                ", category=" + category +
                ", authors=" + authors +
                ", price=" + price +
                ", nrOfCopies=" + nrOfCopies +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.getTitle());
    }
}
