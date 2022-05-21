package VideoGamesAPITesting;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import javax.annotation.meta.When;

import java.util.PriorityQueue;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static io.restassured.RestAssured.*;

public class gamesAPITest {

    ExtentReports reports;

    @BeforeTest
    public void configReports(){
        String path =System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter =new ExtentSparkReporter(path);

        reporter.config().setDocumentTitle("Video Games API Testing");
        reporter.config().setReportName("API Test");

        reports= new ExtentReports();
        reports.attachReporter(reporter);
    }

    @Test(priority = 1)
    public void getVideosGames(){

        reports.createTest("Returns all the videos games in the DB");
        baseURI = "http://localhost:8080/app/";

        when().get("/videogames").then().statusCode(200);
        reports.flush();

    }

    @Test(priority = 2)
    public void addNewGame(){

        reports.createTest("Add a new video game to the DB");
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
        reports.flush();
    }

    @Test(priority = 5)
    public void deleteData(){
        reports.createTest("Deletes a video game from the DB by ID");
        baseURI = "http://localhost:8080/app/";

        Response response = when().delete("videogames/29")
                .then().statusCode(200).log().body().extract().response();

        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains("Record Deleted Successfully"));
        reports.flush();
    }

    @Test(priority = 3)
    public void getSingleVideoGame(){

        reports.createTest("Returns the details of a single game by ID");

        baseURI = "http://localhost:8080/app/";

        when().get("videogames/14").
                then().statusCode(200).log().body().
                body("videoGame.id",equalTo("14"));
        reports.flush();
    }

    @Test(priority = 4)
    public void editVideoGame(){
        ExtentTest test = reports.createTest("Update an existing video game in the DB by specifying a new body");
        baseURI = "http://localhost:8080/app/";

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id",14);
        jsonObject.put("name", "Mortal Kombat 3");
        jsonObject.put("releaseDate", "2022-05-20T13:30:49.724Z");
        jsonObject.put("reviewScore", 0);
        jsonObject.put("category","Action");
        jsonObject.put("rating", 7.0);

        int statusCode =  given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                .when().put("videogames/14")
                .then().statusCode(200).extract().statusCode();
        if(statusCode == 200)
        {
            test.fail("Status code doesn't match");
        }

        reports.flush();
    }
}
