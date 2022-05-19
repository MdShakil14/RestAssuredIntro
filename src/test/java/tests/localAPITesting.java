package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class localAPITesting {

    @Test
    public void getData(){
        baseURI = "http://localhost:3000/";

        given().get("users").then().statusCode(200).log().body();
    }

    @Test
    public void postData(){
        baseURI = "http://localhost:3000/";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_name","Nasir");
        jsonObject.put("last_name","Sunny");
        jsonObject.put("subject_id",2);

        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonObject.toJSONString()).when().
                post("users").
        then().statusCode(201).log().body();
    }

    @Test
    public void putData(){
        baseURI = "http://localhost:3000/";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_name","Sakib");
        jsonObject.put("last_name","Newaz");
        jsonObject.put("subject_id",2);

        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonObject.toJSONString()).when().
                put("users/2").
                then().statusCode(200).log().body();
    }

    @Test
    public void patchData(){
        baseURI = "http://localhost:3000/";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("last_name","Newaz Chowdhury");

        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonObject.toJSONString()).when().
                patch("users/2").
                then().statusCode(200).log().body();
    }

    @Test
    public void deleteData(){
        baseURI = "http://localhost:3000/";
        when().delete("users/3").then().statusCode(200).log().ifError();
    }



}
