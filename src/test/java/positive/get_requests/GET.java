package positive.get_requests;

import baseURL.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GET extends PetStoreBaseUrl {
/*
    Given
     https://petstore.swagger.io/v2/pet/findByStatus?status=available
     When
     Kullanıcı URL'e bir GET request gönderir
    Then
     HTTP Status Code 200 olmalı
    And
    Content Type "application/json" olmalı
    And
    Listede id değeri 14531453 olan bir eleman olmalı
    And
    Listede name değeri "Pamuk" olan bir eleman olmalı
    And
    Listede name değerleri "Pamuk", "doggie", "fish" olan elemanlar olmalı
    And
    Listede en az 200 tane eleman olmalı
    And
    Listede 500'den az eleman olmalı
    And
    Listenin ilk elemanın category - id değeri 0 olmalı
    And
    Listenin ilk elemanın photoUrls değeri "string" olmalı
    And
    Listenin ilk elemanın tags - id değeri 0 olmalı
    And

     */

    @Test
    public void get() {
        //Set the URL
        spec.pathParams("first","pet","second","findByStatus")
                .queryParams("status","available");
        //Send the request and get the response
        Response response= given(spec).when().get("{first}/{second}");
        //response.prettyPrint();


        //Do assertion
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server","Jetty(9.2.9.v20150224)")
                .body("id",hasItem(14531453))
                .body("name",hasItem("Pamuk"))
                .body("name",hasItems("Pamuk","doggie","fish"))
                .body("id",hasSize(greaterThan(200)))
                .body("id",hasSize(lessThan(1000)))
                .body("[0].category.id",equalTo(0))
                .body("[0].photoUrls[0]",equalTo("string"))
                .body("[0].tags[0].id",equalTo(0));


    }

    @Test
    public void getMap() {
        //Set the URL
        spec.pathParams("first","pet","second","findByStatus");

        //Set query parameters
        Map<String,String> queryParamsMap=new HashMap<>();
        queryParamsMap.put("status","available");

        Response response= given(spec).queryParams(queryParamsMap).when().get("{first}/{second}");
        //response.prettyPrint();

        //Do assertion
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server","Jetty(9.2.9.v20150224)")
                .body("id",hasItem(14531453))
                .body("name",hasItem("Pamuk"))
                .body("name",hasItems("Pamuk","doggie","fish"))
                .body("id",hasSize(greaterThan(200)))
                .body("id",hasSize(lessThan(1000)))
                .body("[0].category.id",equalTo(0))
                .body("[0].photoUrls[0]",equalTo("string"))
                .body("[0].tags[0].id",equalTo(0));
    }
}
