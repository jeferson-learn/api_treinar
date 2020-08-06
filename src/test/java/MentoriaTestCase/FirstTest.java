package MentoriaTestCase;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FirstTest {

    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;


    @Test
    public void teste(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/vnd.tasksmanager.v2")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();

        given()
                .spec(requestSpecification)
                .when()
                .then()
                .log().body()
                .spec(responseSpecification);
    }
}
