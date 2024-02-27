package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoqa {
    private String userName = "NickSem";
    private String password = "1qazXSW@";
    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse login (){
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", password);
       return given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .post(EndPointsDemoqa.LOGIN_LOCATOR)
                .then()
                .spec(responseSpecification);
    }

    public List<Map> getBooksList(){
       return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsDemoqa.BOOK_STORE_LOCATOR)
                .then()
                .spec(responseSpecification)
                .extract().response().jsonPath().getList("books", Map.class);

    }


}
