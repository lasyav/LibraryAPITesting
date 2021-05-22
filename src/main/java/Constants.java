import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {
    private String uniqueisbn;
    private String uniqueaisle;
    public void generateUniqueID() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime now = LocalDateTime.now();
        uniqueisbn = date.format(now);
        uniqueaisle = time.format(now);
    }
    public String getIsbn(){
        return uniqueisbn;
    }
    public String getAisle(){
        return uniqueaisle;
    }
}

