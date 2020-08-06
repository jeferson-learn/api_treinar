package MentoriaTestCase;

import Models.CreateUserModel;
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

public class TesteContrato {

    private static String id;
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private static final CreateUserModel contactModel = new CreateUserModel();

    @BeforeEach
    public void setUp(){

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .setBody(contactModel)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void criarContatoComObjetoJson(){
        id = given()
                .spec(requestSpec)
            .when()
                .post()
            .then()
                .log().body()
                .spec(responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas" + File.separator + "JsonSchema.json"))
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
