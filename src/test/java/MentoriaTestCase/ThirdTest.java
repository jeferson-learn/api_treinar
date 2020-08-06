package MentoriaTestCase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ThirdTest {

    private static String id;
    private static RequestSpecification requestSpec;
    private static RequestSpecification requestSpecPost;
    private static RequestSpecification requestSpecPatch;
    private static ResponseSpecification responseSpec;

    @BeforeEach
    public void setUp() {

        JSONObject requestParamsJsonPatch = new JSONObject();
        requestParamsJsonPatch.put("name", "JefTest");

        JSONObject requestParamsJson = new JSONObject();
        requestParamsJson.put("name", "Automacao");
        requestParamsJson.put("last_name", "DBServer");
        requestParamsJson.put("email", "jefy1@test.com.br");
        requestParamsJson.put("age", "27");
        requestParamsJson.put("phone", "99997788");
        requestParamsJson.put("address", "Rua DBServer");
        requestParamsJson.put("state", "Rio Grande do Sul");
        requestParamsJson.put("city", "Porto Alegre");

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        requestSpecPost = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .setBody(requestParamsJson.toString())
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        requestSpecPatch = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .setBody(requestParamsJsonPatch.toString())
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .build();
    }

    @Test
    @Order(1)
    public void criarContatoComObjetoJson() {
        id = given()
            .spec(requestSpecPost)
        .when()
            .post()
        .then()
            .spec(responseSpec)
            .and()
            .log().body()
            .extract().path("data.id");
    }
    @Test
    @Order(2)
    public void pesquisarContato(){
        given()
            .spec(requestSpec)
        .when()
            .get("/"+id)
        .then()
            .log().body();
    }
    @Test
    @Order(3)
    public void editarParteDoContato(){
        given()
            .spec(requestSpecPatch)
        .when()
            .patch("/"+id)
        .then()
            .statusCode(200)
            .log().body();
    }
    @Test
    @Order(4)
    public void pesquisarContatoDepoisDeEditar(){
        given()
            .spec(requestSpec)
        .when()
            .get("/"+id)
        .then()
            .log().body();
    }

    @Test
    @Order(5)
    public void deletaContato(){
        given()
            .spec(requestSpec)
        .when()
            .delete("/"+id)
        .then()
            .statusCode(204);
    }
}