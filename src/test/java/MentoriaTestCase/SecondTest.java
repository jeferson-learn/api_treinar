package MentoriaTestCase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SecondTest {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeEach
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

    @Test
    public void listarContatosStatusCode200Positivo() {
        given()
                .spec(requestSpec)
                .when()
                .get()
                .then()
                .log().body()
                .spec(responseSpec)
                .statusCode(200);
    }
    @Test
    public void listarContatosId46StatusCode200Positivo() {
        given()
                .spec(requestSpec)
                .when()
                .get("/46")
                .then()
                .log().body()
                .spec(responseSpec)
                .statusCode(200);
    }
    @Test
    public void listarContatosStatusCode400Negativo() {
        given()
                .spec(requestSpec)
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .statusCode(400);
    }

}
