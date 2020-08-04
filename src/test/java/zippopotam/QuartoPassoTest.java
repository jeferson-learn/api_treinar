package zippopotam;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class QuartoPassoTest {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpec(){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://api.zippopotam.us")
                .build();
    }

    @Test
    public void requestZipCode90210(){
        given()
                .spec(requestSpec)
        .when()
                .get("us/90210")
        .then()
                .statusCode(200);
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification(){
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

    @Test
    public void requestGet90210(){
        given()
                .spec(requestSpec)
        .when()
                .get("us/90210")
        .then()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }
}
