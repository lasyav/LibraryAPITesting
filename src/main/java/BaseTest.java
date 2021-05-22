import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BaseTest {
    public Response postAddBook(String postUrl, String postRequestBody) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(postRequestBody)
                .when()
                .post(postUrl)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .response();
        return (response);
    }
    public Response getBook(String getUrl, String id) {
        Response response = given()
                .get(getUrl + id)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .response();
        return (response);
    }
    public Response deleteBook(String deleteUrl , String id) {
        String deleteId = "{\n" +
                "    \"ID\":" + id + "\n" +
                "}";
        Response response = given()
                .header("Content-type", "application/json")
                .body(deleteId)
                .and()
                .delete(deleteUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response;
    }
}
