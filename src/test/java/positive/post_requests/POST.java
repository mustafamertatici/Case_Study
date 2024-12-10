package positive.post_requests;

import baseURL.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POST extends PetStoreBaseUrl {
     /*
    Given
     https://petstore.swagger.io/v2/pet
     And
     {
    "id": 14531453,
    "category": {
        "id": 0,
        "name": "Köpek"
    },
    "name": "Pamuk",
    "photoUrls": [
        "string"
    ],
    "tags": [
        {
            "id": 0,
            "name": "Sibirya Kurdu"
        }
    ],
    "status": "available"
}
    When
    Kullanıcı URL'e bir POST request gönderir
    Then
    HTTP Status Code 200 olmalı
    And
    Content Type "application/json" olmalı
    And
    Server değeri "Jetty(9.2.9.v20150224)"olmalı
    And
    Yanıt süresi 2 saniyeden az olmalı
    And
    id değeri "14531453" olmalı
    And
    Name değeri "Pamuk" olmalı
    And
    category - id değeri 0 olmalı
    And
    photoUrls değeri "string" olmalı
    And
    tags-id değeri 0 olmalı
    tags-name değeri Sibirya Kurdu olmalı
     */

    @Test
    public void post() {
        //Set the URL
        spec.pathParam("first","pet");

        //Set the expected data (payLoad)
        String payLoad="{\n" +
                "    \"id\": 14531453,\n" +
                "    \"category\": {\n" +
                "        \"id\": 0,\n" +
                "        \"name\": \"Köpek\"\n" +
                "    },\n" +
                "    \"name\": \"Pamuk\",\n" +
                "    \"photoUrls\": [\n" +
                "        \"string\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "        {\n" +
                "            \"id\": 0,\n" +
                "            \"name\": \"Sibirya Kurdu\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"available\"\n" +
                "}";

        //Send the request and get the response
        Response response=given(spec).body(payLoad).when().post("{first}");
        //response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server","Jetty(9.2.9.v20150224)")
                .time(lessThan(2000L))
                .body("id",equalTo(14531453))
                .body("name",equalTo("Pamuk"))
                .body("category.id",equalTo(0))
                .body("photoUrls[0]",equalTo("string"))
                .body("tags[0].id",equalTo(0))
                .body("tags[0].name",equalTo("Sibirya Kurdu"));
    }

    @Test
    public void postMap() {
        //Set the URL
        spec.pathParam("first","pet");

        //Set the expected data (payLoad)
        Map<String,Object> categoryMap=new HashMap<>();
        categoryMap.put("id",0);
        categoryMap.put("name","Köpek");

        Map<String,Object> tagsMap=new HashMap<>();
        tagsMap.put("id",0);
        tagsMap.put("name","Sibirya Kurdu");

        List<String>photoUrlsList=new ArrayList<>();
        photoUrlsList.add("string");

        List<Map<String,Object>> tagList=new ArrayList<>();
        tagList.add(tagsMap);


        Map<String,Object> requestBody=new HashMap<>();
        requestBody.put("id",14531453);
        requestBody.put("category",categoryMap);
        requestBody.put("name","Pamuk");
        requestBody.put("photoUrls",photoUrlsList);
        requestBody.put("tags",tagList);
        requestBody.put("status","available");

        //Send the request and get the response
        Response response=given(spec)
                .body(requestBody)
                .when()
                .post("{first}");
        //response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server","Jetty(9.2.9.v20150224)")
                .time(lessThan(2000L))
                .body("id",equalTo(14531453))
                .body("name",equalTo("Pamuk"))
                .body("category.id",equalTo(0))
                .body("photoUrls[0]",equalTo("string"))
                .body("tags[0].id",equalTo(0))
                .body("tags[0].name",equalTo("Sibirya Kurdu"));
    }
}
