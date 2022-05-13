package Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;

public class DeleteRequests extends SetProperties {
    private String deletePath="list/";
    PostRequests postRequests = new PostRequests();
    public DeleteRequests() {
        super();
    }

    public String deleteList(){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", postRequests.createSession())
                .when()
                .get(getUrl_host()+deletePath+postRequests.createList())
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .response();
       //Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }
}
