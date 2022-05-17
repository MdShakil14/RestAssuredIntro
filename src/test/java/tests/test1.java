package tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test1 {

    @Test
    public void Test1() {
        Response response = get("https://reqres.in/api/users?page=2");

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getStatusLine());
        System.out.println(response.getContentType());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void Test2() {
        baseURI = "https://reqres.in/api";
        given().get("/users?page=2").then().statusCode(200).body("data[5].first_name",equalTo("Rachel"));
    }
}
