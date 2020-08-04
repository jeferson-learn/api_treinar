package zippopotam;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FirstTest {

    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeEach
    public void setUp(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.zippopotam.us")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

    @Test
    public void healthCheckTest(){
        given()
            .when()
                .get("http://api.zippopotam.us")
            .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void get90210(){
        given()
                .spec(requestSpecification)
        .when()
                .get("/us/90210")
        .then()
//              parte body
                .log().body()
//                .body("places[0].state", containsString("California"));
//                .body("places[0].state", equalTo("California"));
                .body("places[0].'place name'", equalTo("Beverly Hills"));
//                .body("places",hasSize(1));
//                .log().headers()
//                .header("Server.cloudflare", containsString("cloudflare"));
    }

    @Test
    public void testeUS(){

        given()
                .spec(requestSpecification)
        .when()
                .get("/us/02178")
        .then()
                .log().body()
                .spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas" + File.separator + "ZipUSJsonSchema.json"))
                .statusCode(200);
    }

    @Test
    public void testeDE(){

        given()
                .spec(requestSpecification)
        .when()
                .get("/DE/09429")
        .then()
                .log().body()
                .spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas" + File.separator + "ZipDEJsonSchema.json"))
                .statusCode(200);
    }

    @Test
    public void testeBR(){

        given()
                .spec(requestSpecification)
        .when()
                .get("/BR/01000-000")
        .then()
                .log().body()
                .spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas" + File.separator + "ZipBRJsonSchema.json"))
                .statusCode(200);
    }
}
