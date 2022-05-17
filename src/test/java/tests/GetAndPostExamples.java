package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostExamples {

    @Test
    public void getExample() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().
                body("data.first_name", hasItems("Byron", "George")).
                body("data[5].last_name", equalTo("Howell"));
    }

    @Test
    public void postExample() {
        baseURI = "https://reqres.in/api";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", "Shakil");
        jsonObject.put("Job", "AKono nai");

        System.out.println(jsonObject);

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonObject.toJSONString()).
        when().
                post("/users").
        then().
                statusCode(201).
                log().everything();

    }
}
