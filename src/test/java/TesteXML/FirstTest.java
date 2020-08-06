package TesteXML;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FirstTest {

    private String id;
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeEach
    public void setUp() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://restapi.wcaquino.me/usersXML")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

    }

    @Test
    public void criarContatoComObjetoXml(){
        id = given()
                .spec(requestSpec)
                .log().all()
            .when()
                .post()
            .then()
                .log().body()
                .spec(responseSpec)
                .body(RestAssuredMatchers.matchesXsdInClasspath("Schemas" + File.separator + "XmlSchema.xsd"))
                .and()
                .extract().path("data.id");

        deleteContato();
    }

    public void deleteContato(){
        given()
                .spec(requestSpec)
        .when()
                .delete("/"+id)
        .then()
                .statusCode(204);
    }

}
