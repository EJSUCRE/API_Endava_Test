package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;

public class PostRequests extends SetProperties {
    private String login_path="authentication/token/validate_with_login";
    private String creatgesession_path="authentication/session/new";
    private String createListPath="list";
    private String addMoviePath="list/8202983/add_item";
    private String RemoveMoviePath="list/8202983/remove_item";
    GetRequests getRequests = new GetRequests();
    JSONObject jsonObject = new JSONObject();

    public PostRequests() {
        super();
    }

    public String getLoginWithLogin(){
        jsonObject
                .put("username", getUsername())
                .put("password", getPassword())
                .put("request_token",getRequests.getToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+ login_path)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }


    public String createSession(){
        jsonObject
                .put("request_token",getLoginWithLogin());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+ creatgesession_path)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("session_id");
    }

    public String createList(String name, String description, String language){
        jsonObject
            .put("name",name)
            .put("description",description)
            .put("language",language);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+createListPath)
                .then()
                .statusCode(201)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.",response.jsonPath().getString("status_message"));
        return response.jsonPath().getString("list_id");
    }

    public void AddMovieToList(int movieId){
        jsonObject
                .put("media_id",movieId);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+addMoviePath)
                .then()
                .statusCode(201)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was updated successfully.",response.jsonPath().getString("status_message"));

    }

    public void RemoveMovieToList(int movieId){
        jsonObject
                .put("media_id",movieId);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+RemoveMoviePath)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was deleted successfully.",response.jsonPath().getString("status_message"));

    }

}
