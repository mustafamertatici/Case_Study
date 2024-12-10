package negative.get_requests;

import baseURL.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class GET extends PetStoreBaseUrl {
    /*
   Geçersiz bir Endpoint Kullanımı (404 Not Found)
   Given
   https://petstore.swagger.io/v2/pet/findByInvalidStatus?status=available
   When
    Kullanıcı yanlış bir endpoint (findByInvalidStatus) ile GET request gönderir
    Then
     HTTP Status Code 404 not found olmalı
     And
     Type unknown olmalı
     */

    @Test
    public void getInvalidEndpoint() {
        //Set the URL
        spec.pathParams("first","pet","second","findByInvalidStatus")
                .queryParams("status","available");
        //Send the request and get the response
        Response response= given(spec).when().get("{first}/{second}");
        //response.prettyPrint();

        //Do assertion
        response.then()
                .statusCode(404)
                .body("type",equalTo("unknown"));

    }
    /*
    Yanlış bir status parametresi kullanalım swagger görüldüğü üzere available, pending, sold var.
    Bunların yerine olmamayan unknown yazalım.
         Given
         https://petstore.swagger.io/v2/pet/findByStatus?status=unknown
         When
         Kullanıcı yanlış bir status değeri "unknown" ile get request gönderir.
         Then
         HTTP Status Code 200 olur başarılı bir şekilde boş response döner
         And
         Dizinin tamamen boş olduğu kontrol eder.
     */

    @Test
    public void getWrongStatusValue() {
        //Set the URL
        spec.pathParams("first","pet","second","findByStatus");
        spec.queryParams("status","unknown");

        //Send the request and get the response
        Response response= given(spec).when().get("{first}/{second}");
        //response.prettyPrint();


        //Do assertion
        String isEmptyOrNullString;
        response.then()
                .statusCode(200)
                .body(equalTo("[]"));

    }


}
