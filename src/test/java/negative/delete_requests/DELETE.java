package negative.delete_requests;

import baseURL.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DELETE extends PetStoreBaseUrl {



    @Test
    public void deleteMapWithInvalidId() {

        /*
        Silinmesi gereken id değerini doğru bir şekilde "14531453" string verdiğimizde siliniyor ama yanlış değer verirsek
        örnekteki gibi bir string harflerden oluşan bir değerde aldığımız hata üzerine testimizi yapalım.
          Given
         https://petstore.swagger.io/v2/pet/merhaba
         And
         Kullanıcı URL'e bir Delete request gönderir
        Then
         HTTP Status Code 404 olmalı
         And
          type değeri "unknown" olmalı
          And
          message değeri içermeli "NumberFormatException" olmalı

     */

        // Set the URL
        spec.pathParams("first","pet");

        // Set the path parameters using Map so it will be dynamic
        Map<String, Object> pathParamsMap=new HashMap<>();
        pathParamsMap.put("first","pet");
        pathParamsMap.put("id","merhaba");

        //Send the Delete request and get the response
        Response response= given(spec)
                .pathParams(pathParamsMap)
                .when()
                .delete("{first}/{id}");
        //response.prettyPrint();
//Do assertion
        response
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .header("Server","Jetty(9.2.9.v20150224)")
                .body("type",equalTo("unknown"))
                .body("message",containsString("NumberFormatException"));

    }

    @Test
    public void deleteWithoutId() {
        /*
        Id değeri girilmediğinde delete işlemi yapalım.

         https://petstore.swagger.io/v2/pet
         And
         Kullanıcı URL'e bir Delete request gönderir
        Then
         HTTP Status Code 405 olmalı
         */
        // Set the URL
        spec.pathParams("first","pet");

        // Send the DELETE request and get the response
        Response response=given(spec)
                .when()
                .delete("{first}");//Id eksik

        //response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(405);

    }
}
