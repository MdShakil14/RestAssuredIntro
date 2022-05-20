package VideoGamesAPITesting;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.*;

public class gamesAPITest {

    @Test
    public void getVideosGames(){
        baseURI = "http://localhost:8080/app/";

        when().get("/videogames").then().statusCode(200).log().body();

    }

    @Test
    public void addNewGame(){
        baseURI = "http://localhost:8080/app/";

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id",14);
        jsonObject.put("name", "Fifa");
        jsonObject.put("releaseDate", "2022-05-20T13:30:49.724Z");
        jsonObject.put("reviewScore", 0);
        jsonObject.put("category","Action");
        jsonObject.put("rating", 9.0);

        Response response = given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                .when().post("videogames")
                .then().statusCode(200).log().body().extract().response();

        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("Record Added Successfully"));
    }

    @Test
    public void deleteData(){
        baseURI = "http://localhost:8080/app/";

        Response response = when().delete("videogames/13")
                .then().statusCode(200).log().body().extract().response();

        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("Record Deleted Successfully"));
    }

    @Test
    public void getSingleVideoGame(){
        baseURI = "http://localhost:8080/app/";

        when().get("videogames/14").
                then().statusCode(200).log().body().
                body("videoGame.id",equalTo("14"));
    }

    @Test
    public void editVideoGame(){
        baseURI = "http://localhost:8080/app/";

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id",14);
        jsonObject.put("name", "Mortal Kombat 3");
        jsonObject.put("releaseDate", "2022-05-20T13:30:49.724Z");
        jsonObject.put("reviewScore", 0);
        jsonObject.put("category","Action");
        jsonObject.put("rating", 7.0);

        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                .when().put("videogames/14")
                .then().statusCode(200).log().body();


    }
}
