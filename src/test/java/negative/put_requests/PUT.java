package negative.put_requests;

import baseURL.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PUT extends PetStoreBaseUrl {
    /*
    Given
     https://petstore.swagger.io/v2/pet
     And
     {
    "id": 14531453,
    "category": {
        "id": 1,
        "name": "Kedi"
    },
    "name": "Mırmır",
    "photoUrls": [
        "string"
    ],
    "tags": [
        {
            "id": 0,
            "name": "Scottish Fold"
        }
    ],
    "status": "available"
}
Yukarıda put işlemi ile category-id:1, category-name:Kedi, "name": "Mırmır"
, tags-"name": "Scottish Fold" şeklinde güncellenmiştir.

Şimdi bu put ile yaptığımız güncelleme işleminde farklı negatifler yazalım.

    When
    Kullanıcı URL'e bir PUT request gönderir
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
    Name değeri "Mırmır" olmalı
    And
    category - id değeri 1 olmalı
    And
    tags-id değeri 0 olmalı
    tags-name değeri Scottish Fold olmalı
     */


    @Test
    public void putMap() {
        /*
        Burada  photoUrls için  photoUrlsList listesi yerine int bir değer geliyor.
        bunun sonucunda hatalı respense doğrulamaları

         When
        Kullanıcı URL'e bir PUT request gönderir
        Then
        HTTP Status Code 500 olmalı
        And
        "type" değeri "unknown" olmalı
        And
        message something bad içermeli
         */

        //Set the URL
        spec.pathParams("first","pet");

        //Set the expected data (payLoad) hash map
        Map<String,Object> categoryMap=new HashMap<>();
        categoryMap.put("id",1);
        categoryMap.put("name","Kedi");

        Map<String,Object> tagsMap=new HashMap<>();
        tagsMap.put("id",0);
        tagsMap.put("name","Scottish Fold");

        List<String> photoUrlsList=new ArrayList<>();
        photoUrlsList.add("string");

        List<Map<String,Object>> tagList=new ArrayList<>();
        tagList.add(tagsMap);

        Map<String,Object> payLoad=new HashMap<>();
        payLoad.put("id",14531453);
        payLoad.put("category",categoryMap);
        payLoad.put("name","Mırmır");
        payLoad.put("photoUrls",1234); // burada obje bir değer yerine int bir değer geliyor
        payLoad.put("tags",tagList);
        payLoad.put("status","available");


        //Send the request and get the response
        Response response=given(spec)
                .body(payLoad)
                .when()
                .put("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(500)
                .body("type",equalTo("unknown"))
                .body("message",containsString("bad happened"));


    }
}
