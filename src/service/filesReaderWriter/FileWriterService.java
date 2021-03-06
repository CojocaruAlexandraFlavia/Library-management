package service.filesReaderWriter;

import models.*;
import enums.MemberStatus;
import enums.ReservationStatus;

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

    private int addressId, authorId, bookItemId, bookId, borrowingId, categoryId, librarianId, libraryId, memberId, publishingHouseId, reservationId;

    public void initializeIds(){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\";
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
                switch (fileName) {
                    case "Addresses.csv" -> addressId = id + 1;
                    case "Authors.csv" -> authorId = id + 1;
                    case "BookItems.csv" -> bookItemId = id + 1;
                    case "Books.csv" -> bookId = id + 1;
                    case "Borrowings.csv" -> borrowingId = id + 1;
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
        return  line;
    }

    public void writeAddressToFile(Address address){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\Addresses.csv";
        StringBuilder content = new StringBuilder();
        content.append(addressId);
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
        FilesObjects.addresses.put(addressId++, address);
    }


    public void writeCategoryToFile(Category category){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\Categories.csv";
        StringBuilder content = new StringBuilder();
        content.append(categoryId);
        content.append(",");
        content.append(category.getName());
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FilesObjects.categories.put(categoryId++, category);
    }


    public void writePublishingHouseToFile(PublishingHouse publishingHouse){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\PublishingHouses.csv";
        StringBuilder content = new StringBuilder();
        content.append(publishingHouseId);
        content.append(publishingHouse.getName());
        content.append(",");
        content.append(publishingHouse.getDescription());
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FilesObjects.publishingHouses.put(publishingHouseId++, publishingHouse);
    }

    public void writePersonToFile(Person person, int option, Date membershipDate, Date hireDate, String status){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports";
        StringBuilder content = new StringBuilder();
        switch (option){
            case 1:
                path += "Authors.csv";
                content.append(authorId);
                FilesObjects.authors.put(authorId++, (Author)person);
                break;
            case 2:
                path += "Librarians.csv";
                content.append(librarianId);
                    FilesObjects.librarians.put(librarianId++, new Librarian(person.getFirstName(), person.getLastName(),
                            person.getPhoneNumber(), hireDate));
                break;
            case 3:
                path += "Members.csv";
                content.append(memberId);
                    FilesObjects.members.put(memberId, new Member(person.getFirstName(), person.getLastName(),
                            person.getPhoneNumber(), memberId++, membershipDate, Enum.valueOf(MemberStatus.class, status)));
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

    public void writeReservationToFile(BookReservation reservation){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\Reservations.csv";
        StringBuilder content = new StringBuilder();
        content.append(reservationId);
        content.append(",");
        content.append(reservation.getMemberId());
        content.append(",");
        content.append(reservation.getStatus());
        content.append(",");
        content.append(reservation.getBookItem().getItemId());
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FilesObjects.reservations.put(reservationId++, reservation);
    }

    public void writeBorrowingToFile(BookBorrowing borrowing){
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\Borrowings.csv";
        StringBuilder content = new StringBuilder();
        content.append(borrowingId);
        content.append(",");
        content.append(borrowing.getMemberId());
        content.append(",");
        content.append(borrowing.getDueDate());
        content.append(",");
        content.append(borrowing.getBorrowingDay());
        content.append(",");
        content.append(borrowing.getItemId());
        content.append("\n");
        try {
            Files.write(Paths.get(path), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FilesObjects.borrowings.put(borrowingId++, borrowing);
    }


    public static void closeMemberAccount(Member member){
        Member fileMember = FilesObjects.members.get(member.getId());
        fileMember.setStatus(MemberStatus.CLOSED);
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\Members.csv";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        StringBuilder inputBuffer = new StringBuilder();
        inputBuffer.append(scanner.nextLine());
        inputBuffer.append("\n");
        String line, memberLine = "";
        while ((scanner.hasNextLine())){
            line = scanner.nextLine();
            if(Integer.parseInt(line.split(",")[0]) == member.getId()){
                memberLine = line;
            }
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }

        String memberInfo = member.getId() + "," + member.getFirstName() + "," + member.getLastName()+
                "," + member.getPhoneNumber() + "," + new SimpleDateFormat("dd-MM-yyyy").format(member.getMembershipDate()) + "," +
                member.getStatus();

        String inputString = inputBuffer.toString();
        inputString = inputString.replace(memberLine,memberInfo);

        try {
            Files.write(Paths.get(path), inputString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buyABook(BookReservation reservation){
        BookReservation fileReservation = FilesObjects.reservations.get(reservation.getReservationId());
        fileReservation.setStatus(ReservationStatus.COMPLETED);
        String path = "D:\\IntelliJ projects\\Library-management\\src\\reports\\Reservations.csv";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        StringBuilder inputBuffer = new StringBuilder();
        inputBuffer.append(scanner.nextLine());
        inputBuffer.append("\n");
        String line, reservationLine = "";
        while ((scanner.hasNextLine())){
            line = scanner.nextLine();
            if(Integer.parseInt(line.split(",")[0]) == reservation.getReservationId()){
                reservationLine = line;
            }
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }

        String reservationInfo = reservation.getReservationId() + "," + reservation.getMemberId() + "," +reservation.getStatus() +
                "," + reservation.getBookItem().getItemId();
        String inputString = inputBuffer.toString();
        inputString = inputString.replace(reservationLine, reservationInfo);

        try {
            Files.write(Paths.get(path), inputString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAddressId() {
        return addressId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getBookItemId() {
        return bookItemId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getPublishingHouseId() {
        return publishingHouseId;
    }

    public int getReservationId() {
        return reservationId;
    }
}
