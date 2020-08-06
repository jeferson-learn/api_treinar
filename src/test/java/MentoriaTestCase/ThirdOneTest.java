package MentoriaTestCase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ThirdOneTest {

    private static String id;
    private static RequestSpecification requestSpecification;
    private static RequestSpecification requestSpecEditar;
    private static RequestSpecification requestSpecCriar;
    private static ResponseSpecification responseSpecification;

    @BeforeEach
    public void setUp(){

        JSONObject requestParamsJsonEditar = new JSONObject();
        requestParamsJsonEditar.put("name", "Daniella");

        JSONObject requestParamsJsonCriar = new JSONObject();
        requestParamsJsonCriar.put("name", "J");
        requestParamsJsonCriar.put("last_name", "L");
        requestParamsJsonCriar.put("email", "jeferson2@test.com.br");
        requestParamsJsonCriar.put("age", "27");
        requestParamsJsonCriar.put("phone", "99997788");
        requestParamsJsonCriar.put("address", "Rua DBServer");
        requestParamsJsonCriar.put("state", "Rio Grande do Sul");
        requestParamsJsonCriar.put("city", "Porto Alegre");

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        requestSpecCriar = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .setBody(requestParamsJsonCriar.toString())
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        requestSpecEditar = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .setBody(requestParamsJsonEditar.toString())
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .build();
    }

    @Test
    public void criarContatoObjetoJson(){
        id = given()
                .spec(requestSpecCriar)
        .when()
                .post()
        .then()
                .spec(responseSpecification)
                .and()
                .log().body()
                .extract().path("data.id");
    }

    @Test
    public void listasContato(){
        given()
                .spec(requestSpecification)
        .when()
                .get()
        .then()
                .log().body()
                .spec(responseSpecification);
    }

    @Test
    public void editarContato(){
        given()
                .spec(requestSpecEditar)
        .when()
                .patch("/47")
        .then()
                .log().body();
    }

    // OK
    @Test
    public void deleteContato(){
        given()
                .spec(requestSpecification)
        .when()
                .delete("/47")
        .then()
                .log().body()
                .statusCode(204);
    }
}
