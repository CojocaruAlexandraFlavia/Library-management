package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditReportGeneratorService {

    private static final String reportPath = "C:\\Users\\alexa\\Desktop\\FMI\\AN II\\PAO\\Project\\src\\Reports\\";

    private AuditReportGeneratorService(){}

    private  static class SINGLETON_HOLDER{
        private static final AuditReportGeneratorService INSTANCE = new AuditReportGeneratorService();
    }

    public static AuditReportGeneratorService getInstance(){
        return SINGLETON_HOLDER.INSTANCE;
    }

    public String generateAuditReport(){
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("ACTION_NAME");
        reportContent.append(", ");
        reportContent.append("DATE");
        reportContent.append("\n");
        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = reportPath + "Audit" + fileExtension + ".csv";
        try {
            Files.createFile(Paths.get(filePath));
            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public void addActionToReport(int option, String reportPath){
        StringBuilder reportContent = new StringBuilder();
        String content = switch (option) {
            case 1 -> "A person was verified if he is a member or not.";
            case 2 -> "A book has been added.";
            case 3 -> "A new library has been added.";
            case 4 -> "A new book category has been added.";
            case 5 -> "A new member has been added.";
            case 6 -> "A new publishing house has been added.";
            case 7 -> "A new librarian has been added.";
            case 8 -> "A new author has been added.";
            case 9 -> "A book has been reserved.";
            case 10 -> "A book has been borrowed.";
            case 11 -> "A book has been bought.";
            case 12 -> "All the book titles have been shown";
            case 13 -> "All the available titles have been shown.";
            case 14 -> "The total sales value has been shown.";
            case 15 -> "A member account has been closed.";
            default -> "";
        };
        reportContent.append(content);
        reportContent.append(", ");
        reportContent.append(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        reportContent.append("\n");
        try {
            Files.write(Paths.get(reportPath), reportContent.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
