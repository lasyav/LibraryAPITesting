import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TestLibrary  extends BaseTest {
    public String addBookBody;
    public String isbn;
    public String aisle;

    public void setup(){
        RestAssured.baseURI = "http://216.10.245.166";
        Constants uniqueID = new Constants();
        uniqueID.generateUniqueID();
        isbn = uniqueID.getIsbn();
        aisle = uniqueID.getAisle();
        addBookBody = "{\n" +
                "\"name\":\"Shade\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"MayamBialik\"\n" +
                "}";
    }

    @Test
    public void testAddBook() {
        setup();
        Response response = postAddBook("/Library/Addbook.php", addBookBody);
        assertEquals(response.jsonPath().getString("Msg"), "successfully added");
    }
    @Test
    public void testGetBookByID() {
        setup();
        Response requestOutput = postAddBook("/Library/Addbook.php", addBookBody);
        Response output = getBook("/Library/GetBook.php?ID=", requestOutput.jsonPath().getString("ID"));
        assertEquals(output.jsonPath().getString("author[0]"),"MayamBialik");
    }
    @Test
    public void testGetBookByAuthor(){
        setup();
        Response requestOutput = postAddBook("/Library/Addbook.php", addBookBody);
        Response output = getBook("/Library/GetBook.php?AuthorName=" ,"MayamBialik");
        assertEquals(output.jsonPath().getString("book_name[0]"),"Shade");
    }
    @Test
    public void testDeleteBook(){
        setup();
        Response requestOutput = postAddBook("/Library/Addbook.php" , addBookBody);
        Response output = deleteBook("/Library/DeleteBook.php",requestOutput.jsonPath().getString("ID"));
        assertEquals(output.jsonPath().getString("msg"),"book is successfully deleted");
    }
}