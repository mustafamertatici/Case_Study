package negative.post_requests;

import baseURL.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class POST  extends PetStoreBaseUrl {

    /*
    Null değerlerin olduğu negatif testlerde düşünüyordum. NULL yerine radom değer kendisi tanımlıyor.
     */


    @Test
    public void postMapWithoutId() {
        /*
        Geçersiz bir veri türü girişini kontrol ediyoruz. Id değeri olarak string değer girerek.

        When
        Kullanıcı URL'e bir POST request gönderir
        Then
        HTTP Status Code 500 olmalı
        And
        code değeri 500 olmalı
        And
        type değeri unknown olmalı
        And
        message değeri bad happened içermeli.
         */
        //Set the URL
        spec.pathParam("first", "pet");

        //Set the expected data (payLoad)
        Map<String, Object> categoryMap = new HashMap<>();
        categoryMap.put("id", 0);
        categoryMap.put("name", "Köpek");

        Map<String, Object> tagsMap = new HashMap<>();
        tagsMap.put("id", 0);
        tagsMap.put("name", "Sibirya Kurdu");

        List<String> photoUrlsList = new ArrayList<>();
        photoUrlsList.add("string");

        List<Map<String, Object>> tagList = new ArrayList<>();
        tagList.add(tagsMap);


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id","invalid");
        requestBody.put("category", categoryMap);
        requestBody.put("name", "Pamuk");
        requestBody.put("photoUrls", photoUrlsList);
        requestBody.put("tags", tagList);
        requestBody.put("status", "available");

        //Send the request and get the response
        Response response = given(spec)
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .post("{first}");
        //response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(500)
                .body("type",equalTo("unknown"))
                .body("message",containsString("bad happened"));

    }
    }