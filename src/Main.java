import Classes.*;
import Enums.MemberStatus;
import Service.MainService;

import java.util.*;

public class Main {
    public static void showMenu(){
        System.out.println("Choose one option:");
        System.out.println("1. Verify your member id.");
        System.out.println("2. Add a book.");
        System.out.println("3. Add an address of a library.");
        System.out.println("4. Add a library.");
        System.out.println("5. Add a category for books.");
        System.out.println("6. Add a a new member.");
        System.out.println("7. Add a new publishing house.");
        System.out.println("8. Add a new librarian.");
        System.out.println("9. Add a new author.");
        System.out.println("10. Make a book reservation.");
        System.out.println("11. Make a book lending.");
        System.out.println("12. Show all the book titles");
        System.out.println("13. Show the total value of the purchased books.");
        System.out.println("14. Close your member account.");
        System.out.println("15. Stop the program");
    }
    public static void main(String[] args) {
        MainService mainService = new MainService();
        Scanner scanner = new Scanner (System.in);
        int memberIds = 0;
        while(true){
            showMenu();
            int option = Integer.parseInt(scanner.nextLine());
            switch (option){
                case 1:
                    System.out.println("Write your member id.");
                    int id =  Integer.parseInt(scanner.nextLine());
                    if(mainService.verifyMember(id)){
                        System.out.println("You are not a member. You can create an account if you want by choosing number 6 next time.");
                    }
                    else {
                        System.out.println("It's ok, you are a member.");
                    }
                    break;
                case 2:
                    System.out.println("Write the book title.");
                    String title = scanner.nextLine();
                    System.out.println("Write the book subject.");
                    String subject = scanner.nextLine();
                    System.out.println("Write the language in which the book is written.");
                    String language = scanner.nextLine();
                    System.out.println("Write the number of pages of the book.");
                    int numberOfPages = Integer.parseInt(scanner.nextLine());
                    System.out.println("Does the publishing house already exist? YES or NO");
                    String response = scanner.nextLine();
                    PublishingHouse publishingHouse = new PublishingHouse();
                    if(response.equalsIgnoreCase("YES")){
                        System.out.println("Write the publishing house name.");
                        String name = scanner.nextLine();
                        if(mainService.publishingHouseSearch(name).getName().equals("")){
                            System.out.println("The name is incorrect. You can try again later.");
                        }
                        else{
                            publishingHouse.setName(mainService.publishingHouseSearch(name).getName());
                            publishingHouse.setDescription(mainService.publishingHouseSearch(name).getDescription());
                        }
                    }
                    else if(response.equalsIgnoreCase("NO")){
                        System.out.println("Then write the publishing house name.");
                        String name = scanner.nextLine();
                        System.out.println("Now you can write the publishing house description.");
                        String description = scanner.nextLine();
                        publishingHouse.setName(name);
                        publishingHouse.setDescription(description);
                        mainService.addPublishingHouse(publishingHouse);
                    }
                    else {
                        System.out.println("Wrong answer.Try again later.");
                        break;
                    }

                    System.out.println("Does the category already exist? YES or NO");
                    String response2 = scanner.nextLine();
                    Category category = new Category();
                    if(response2.equalsIgnoreCase("YES")){
                        System.out.println("Write the category name.");
                        String name = scanner.nextLine();
                        if(mainService.categorySearch(name).getName().equals("")){
                            System.out.println("The name is incorrect. You can try again later.");
                        }
                        else{
                            category.setName(mainService.publishingHouseSearch(name).getName());
                        }
                    }
                    else if(response2.equalsIgnoreCase("NO")){
                        System.out.println("Then write the category name.");
                        String name = scanner.nextLine();
                        category.setName(name);
                        mainService.addCategory(category);
                    }
                    else {
                        System.out.println("Wrong answer.Try again later.");
                        break;
                    }
                    List<Author> authors = new ArrayList<>();
                    System.out.println("How many authors does the book have?");
                    int numberOfAuthors = Integer.parseInt(scanner.nextLine());
                    for(int i = 0; i < numberOfAuthors; i++){
                        System.out.println("Does the author already exist? YES or NO");
                        String response3 = scanner.nextLine();
                        Author author = new Author();
                        boolean wrongAnswer = false;
                        if(response3.equalsIgnoreCase("YES")){
                            System.out.println("Write the first name.");
                            String firstName = scanner.nextLine();
                            System.out.println("Write the last name.");
                            String lastName = scanner.nextLine();
                            if(mainService.authorSearch(firstName,lastName).getFirstName().equals("")){
                                System.out.println("The first name or the last name is incorrect. You can try again later.");
                            }
                            else{
                                author.setFirstName(mainService.authorSearch(firstName,lastName).getFirstName());
                                author.setLastName(mainService.authorSearch(firstName,lastName).getLastName());
                            }
                        }
                        else if(response3.equalsIgnoreCase("NO")){
                            System.out.println("Then write the first name.");
                            String firstName = scanner.nextLine();
                            System.out.println("Now write the last name.");
                            String lastName = scanner.nextLine();
                            author.setFirstName(firstName);
                            author.setLastName(lastName);
                            mainService.addAuthor(author);
                        }
                        else {
                            System.out.println("Wrong answer.Try again later.");
                            break;
                        }
                        if(!author.getFirstName().equals("")){
                            authors.add(author);
                        }

                    }
                    Book book = new Book(title, subject,language,numberOfPages,publishingHouse,authors,category);
                    mainService.addBook(book);
                    break;

                case 3:
                    System.out.println("Write the street.\n");
                    String street = scanner.nextLine();
                    System.out.println("Write the city.\n");
                    String city = scanner.nextLine();
                    System.out.println("Write the country.\n");
                    String country = scanner.nextLine();
                    System.out.println("And the zipcode.. .\n");
                    String zipCode = scanner.nextLine();
                    Address address = new Address(street,city,country,zipCode);
                    break;
                case 4:
                    System.out.println("Write the library name.");
                    String name = scanner.nextLine();
                    System.out.println("Write the address: first, the street.");
                    String addressStreet = scanner.nextLine();
                    System.out.println("Now the city.");
                    String addressCity = scanner.nextLine();
                    System.out.println("The country..");
                    String addressCountry = scanner.nextLine();
                    System.out.println("Finally, the zip code.");
                    String addressZipCode = scanner.nextLine();
                    System.out.println("How many librarians will work there?");
                    int librariansNumber = Integer.parseInt(scanner.nextLine());
                    List<Librarian> librarians = new ArrayList<>();
                    for(int i = 0; i < librariansNumber; i++){
                        System.out.println("Write the first name.");
                        String firstName = scanner.nextLine();
                        System.out.println("Write the last name.");
                        String lastName = scanner.nextLine();
                        System.out.println("Write the phone number.");
                        String phoneNumber = scanner.nextLine();
                        System.out.println("Write the hiring date like day-month-year");
                        String[] hiringDate = scanner.nextLine().split("-");
                        Librarian librarian = new Librarian(firstName,lastName,phoneNumber, new Date(Integer.parseInt(hiringDate[0]), Integer.parseInt(hiringDate[1]), Integer.parseInt(hiringDate[2])));
                        mainService.addLibrarian(librarian);
                        librarians.add(librarian);
                    }
                    System.out.println("How many books the library will have at the beginning?");
                    int numberOfBooks = Integer.parseInt(scanner.nextLine());
                    List<Book> books = new ArrayList<>();
                    for(int i = 0; i < numberOfBooks; i++){
                        System.out.println("Write the book title.");
                        String bookTitle = scanner.nextLine();
                        System.out.println("Write the book subject.");
                        String bookSubject = scanner.nextLine();
                        System.out.println("Write the language in which the book is written.");
                        String bookLanguage = scanner.nextLine();
                        System.out.println("Write the number of pages of the book.");
                        int bookNumberOfPages = Integer.parseInt(scanner.nextLine());
                        System.out.println("Does the publishing house already exist? YES or NO");
                        String response3 = scanner.nextLine();
                        PublishingHouse ph = new PublishingHouse();
                        if(response3.equalsIgnoreCase("YES")){
                            System.out.println("Write the publishing house name.");
                            String pHName = scanner.nextLine();
                            if(mainService.publishingHouseSearch(pHName).getName().equals("")){
                                System.out.println("The name is incorrect. You can try again later.");
                            }
                            else{
                                ph.setName(mainService.publishingHouseSearch(name).getName());
                                ph.setDescription(mainService.publishingHouseSearch(name).getDescription());
                            }
                        }
                        else if(response3.equalsIgnoreCase("NO")){
                            System.out.println("Then write the publishing house name.");
                            String pHName = scanner.nextLine();
                            System.out.println("Now you can write the publishing house description.");
                            String description = scanner.nextLine();
                            ph.setName(pHName);
                            ph.setDescription(description);
                            mainService.addPublishingHouse(ph);
                        }
                        else {
                            System.out.println("Wrong answer.Try again later.");
                            break;
                        }

                        System.out.println("Does the category already exist? YES or NO");
                        String response4 = scanner.nextLine();
                        Category category2 = new Category();
                        if(response4.equalsIgnoreCase("YES")){
                            System.out.println("Write the category name.");
                            String categoryName = scanner.nextLine();
                            if(mainService.categorySearch(categoryName).getName().equals("")){
                                System.out.println("The name is incorrect. You can try again later.");
                            }
                            else{
                                category2.setName(mainService.publishingHouseSearch(name).getName());
                            }
                        }
                        else if(response4.equalsIgnoreCase("NO")){
                            System.out.println("Then write the category name.");
                            String categoryName = scanner.nextLine();
                            category2.setName(categoryName);
                            mainService.addCategory(category2);
                        }
                        else {
                            System.out.println("Wrong answer.Try again later.");
                            break;
                        }
                        List<Author> bookAuthors = new ArrayList<>();
                        System.out.println("How many authors does the book have?");
                        int bookNumberOfAuthors = Integer.parseInt(scanner.nextLine());
                        for(int j = 0; j < bookNumberOfAuthors; j++){
                            System.out.println("Does the author already exist? YES or NO");
                            String response5 = scanner.nextLine();
                            Author author = new Author();
                            if(response5.equalsIgnoreCase("YES")){
                                System.out.println("Write the first name.");
                                String firstName = scanner.nextLine();
                                System.out.println("Write the last name.");
                                String lastName = scanner.nextLine();
                                if(mainService.authorSearch(firstName,lastName).getFirstName().equals("")){
                                    System.out.println("The first name or the last name is incorrect. You can try again later.");
                                }
                                else{
                                    author.setFirstName(mainService.authorSearch(firstName,lastName).getFirstName());
                                    author.setLastName(mainService.authorSearch(firstName,lastName).getLastName());
                                }
                            }
                            else if(response5.equalsIgnoreCase("NO")){
                                System.out.println("Then write the first name.");
                                String firstName = scanner.nextLine();
                                System.out.println("Now write the last name.");
                                String lastName = scanner.nextLine();
                                author.setFirstName(firstName);
                                author.setLastName(lastName);
                                mainService.addAuthor(author);
                            }
                            else {
                                System.out.println("Wrong answer.Try again later.");
                                break;
                            }
                            bookAuthors.add(author);
                        }
                        Book book2 = new Book(bookTitle, bookSubject, bookLanguage, bookNumberOfPages,ph,bookAuthors, category2);
                        mainService.addBook(book2);
                        books.add(book2);
                    }

                    Library library = new Library(name, new Address(addressStreet, addressCity, addressCountry, addressZipCode), librarians, books);
                    mainService.addLibrary(library);
                    break;
                case 5:
                    System.out.println("Write the category name");
                    String categoryName = scanner.nextLine();
                    Category category1 = new Category(categoryName);
                    mainService.addCategory(category1);
                    break;
                case 6:
                    System.out.println("Write the first name.");
                    String firstName = scanner.nextLine();
                    System.out.println("Write the last name.");
                    String lastName = scanner.nextLine();
                    System.out.println("Write the phone number.");
                    String phoneNumber = scanner.nextLine();
                    Member member = new Member(firstName, lastName, phoneNumber, memberIds++, new Date(), MemberStatus.ACTIVE);
                    mainService.addMember(member);
                    break;
                case 7:
                    System.out.println("Write the name.");
                    String phName = scanner.nextLine();
                    System.out.println("Write the description.");
                    String description = scanner.nextLine();
                    PublishingHouse publishingHouse1 = new PublishingHouse(phName, description);
                    mainService.addPublishingHouse(publishingHouse1);
                    break;
                case 8:
                    System.out.println("Write the first name.");
                    String librarianFirstName = scanner.nextLine();
                    System.out.println("Write the last name.");
                    String librarianLastName = scanner.nextLine();
                    System.out.println("Write the phone number.");
                    String librarianPhoneNumber = scanner.nextLine();
                    Librarian librarian = new Librarian(librarianFirstName, librarianLastName, librarianPhoneNumber, new Date());
                    mainService.addLibrarian(librarian);
                    break;
                case 9:
                    System.out.println("Write the first name.");
                    String authorFirstName = scanner.nextLine();
                    System.out.println("Write the last name.");
                    String authorLastName = scanner.nextLine();
                    System.out.println("Write the phone number.");
                    String authorPhoneNumber = scanner.nextLine();
                    Author author = new Author(authorFirstName, authorLastName, authorPhoneNumber);
                    mainService.addAuthor(author);
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    mainService.showBookTitles();
                    break;
                case 13:
                    mainService.showTotalSales();
                    break;
                case 14:
                    System.out.println("Write your member id.");
                    int memberId = Integer.parseInt(scanner.nextLine());
                    if(mainService.verifyMember(memberId)){
                        mainService.closeMemberAccount(memberId);
                    }
                    else{
                        System.out.println("Wrong member id. Try to write correctly next time.");
                    }
                    break;
                case 15:
                    System.out.println("Stopping system..");
                    System.exit(0);
                default:
                    System.out.println("Write a correct option.");

            }
        }
    }
}
