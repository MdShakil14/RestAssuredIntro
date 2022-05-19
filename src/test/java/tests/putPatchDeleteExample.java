package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class putPatchDeleteExample {

    @Test
    public void putExample() {

        baseURI = "https://reqres.in/";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Shakil");
        jsonObject.put("job", "Bekar");

        given().header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                contentType(ContentType.JSON).
                body(jsonObject.toJSONString()).
                when().put("api/users?page=2").
                then().statusCode(200).log().everything();
    }

    @Test
    public void patchExample() {

        baseURI = "https://reqres.in/";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Shakil");
        jsonObject.put("job", "Bekar");

        given().header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                contentType(ContentType.JSON).
                body(jsonObject.toJSONString()).
                when().patch("api/users?page=2").
                then().statusCode(200).log().everything();
    }

    @Test
    public void deleteExample() {

        baseURI = "https://reqres.in/";

        when().delete("api/users/2").then().statusCode(204).log().everything();
    }
}
