package Service;

import Classes.Address;
import Classes.Category;
import Classes.Person;
import Classes.PublishingHouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileWriterService {

    private FileWriterService(){initializeIds();}

    private  static class SINGLETON_HOLDER{
        private static final FileWriterService INSTANCE = new FileWriterService();
    }

    public static FileWriterService getInstance(){
        return FileWriterService.SINGLETON_HOLDER.INSTANCE;
    }

    private int addressId, authorId, bookItemId, bookId, borrowId, categoryId, librarianId, libraryId, memberId, publishingHouseId, reservationId;

    public void initializeIds(){
        String path = "C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\";
        List<String> fileNames = new ArrayList<>();
        fileNames.add("Addresses.csv");
        fileNames.add("Authors.csv");
        fileNames.add("BookItems.csv");
        fileNames.add("Books.csv");
        fileNames.add("Borrowings.csv");
        fileNames.add("Categories.csv");
        fileNames.add("Librarians.csv");
        fileNames.add("Libraries.csv");
        fileNames.add("Members.csv");
        fileNames.add("PublishingHouses.csv");
        fileNames.add("Reservations.csv");
        for(String fileName : fileNames){
            try {
                Scanner scanner = new Scanner(new File(path + fileName));
                String lastLine = getLastLine(scanner);
                int id = Integer.parseInt(lastLine.split(",")[0]);
                System.out.println(id);
                switch (fileName) {
                    case "Addresses.csv" -> addressId = id + 1;
                    case "Authors.csv" -> authorId = id + 1;
                    case "BookItems.csv" -> bookItemId = id + 1;
                    case "Books.csv" -> bookId = id + 1;
                    case "Borrowings.csv" -> borrowId = id + 1;
                    case "Categories.csv" -> categoryId = id + 1;
                    case "Librarians.csv" -> librarianId = id + 1;
                    case "Libraries.csv" -> libraryId = id + 1;
                    case "Members.csv" -> memberId = id + 1;
                    case "PublishingHouses.csv" -> publishingHouseId = id + 1;
                    case "Reservations.csv" -> reservationId = id + 1;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public String getLastLine(Scanner scanner){
        String line = "";
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
        }
        System.out.println(line);
        return  line;
    }

    public void writeAddressToFile(Address address){
        String path = "C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Addresses.csv";
        StringBuilder content = new StringBuilder();
        content.append(addressId++);
        content.append(",");
        content.append(address.getStreet());
        content.append(",");
        content.append(address.getCity());
        content.append(",");
        content.append(address.getCountry());
        content.append(",");
        content.append(address.getZipCode());
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeCategoryToFile(Category category){
        String path = "C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\Categories.csv";
        StringBuilder content = new StringBuilder();
        content.append(categoryId++);
        content.append(",");
        content.append(category.getName());
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writePublishingHouseToFile(PublishingHouse publishingHouse){
        String path = "C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\PublishingHouses.csv";
        StringBuilder content = new StringBuilder();
        content.append(publishingHouseId++);
        content.append(publishingHouse.getName());
        content.append(",");
        content.append(publishingHouse.getDescription());
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePersonToFile(Person person, int option, Date membershipDate, Date hireDate, String status){
        String path = "C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\";
        StringBuilder content = new StringBuilder();
        switch (option){
            case 1:
                path += "Authors.csv";
                content.append(authorId++);
                break;
            case 2:
                path += "Librarians.csv";
                content.append(librarianId++);
                break;
            case 3:
                path += "Members.csv";
                content.append(memberId++);
                break;
            default:
                break;
        }
        content.append(",");
        content.append(person.getFirstName());
        content.append(",");
        content.append(person.getLastName());
        content.append(",");
        content.append(person.getPhoneNumber());
        content.append(",");
        switch (option) {
            case 2 -> content.append(new SimpleDateFormat("dd-MM-yyyy").format(hireDate));
            case 3 -> {
                content.append(new SimpleDateFormat("dd-MM-yyyy").format(membershipDate));
                content.append(",");
                content.append(status);
            }
        }
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
