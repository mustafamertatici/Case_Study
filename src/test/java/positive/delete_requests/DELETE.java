package positive.delete_requests;

import baseURL.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DELETE  extends PetStoreBaseUrl {
    /*Given
     https://petstore.swagger.io/v2/pet/14531453
     And
     Kullanıcı URL'e bir DELETE request gönderir
    Then
     HTTP Status Code 200 olmalı
     And
      type değeri "unknown" olmalı
      And
      message değeri "14531453" olmalı
     */

    @Test
    public void delete() {
        // Set the URL
        spec.pathParams("first","pet","second","14531453");

        // Send the DELETE request and get the response
        Response response=given(spec)
                .when()
                .delete("{first}/{second}");
        //response.prettyPrint();

            //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server","Jetty(9.2.9.v20150224)")
                .body("type",equalTo("unknown"))
                .body("message",equalTo("14531453"));

    }

    @Test
    public void deleteWithMap() {
        // Set the URL
        spec.pathParams("first","pet");

        // Set the path parameters using Map so it will be dynamic
        Map<String, Object> pathParamsMap=new HashMap<>();
        pathParamsMap.put("first","pet");
        pathParamsMap.put("id",14531453);

        //Send the Delete request and get the response
        Response response= given(spec)
                .pathParams(pathParamsMap)
                .when()
                .delete("{first}/{id}");
        //response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server","Jetty(9.2.9.v20150224)")
                .body("type",equalTo("unknown"))
                .body("message",equalTo("14531453"));
    }
}
