package APITarefas;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class APITarefasTest {

    private static RequestSpecification requestSpec;
    private static RequestSpecification requestSpecPost;
    private static RequestSpecification requestSpecPatch;
    private static ResponseSpecification responseSpec;
    private static String id;

    @BeforeEach
    public void setUp() {

        JSONObject criaContatoJson = new JSONObject();
        criaContatoJson.put("name", "Automacao");
        criaContatoJson.put("last_name", "DBServer");
        criaContatoJson.put("email", "jefy1@test.com.br");
        criaContatoJson.put("age", "27");
        criaContatoJson.put("phone", "99997788");
        criaContatoJson.put("address", "Rua DBServer");
        criaContatoJson.put("state", "Rio Grande do Sul");
        criaContatoJson.put("city", "Porto Alegre");

        JSONObject editarJson = new JSONObject();
        editarJson.put("name", "JefyTest");

        requestSpecPost = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .setBody(criaContatoJson.toString())
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .build();
    }

    @Test
    public void listarContato(){
        given()
                .spec(requestSpec)
        .when()
                .get("/contacts")
        .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void criarContato(){
        id = given()
                .spec(requestSpecPost)
        .when()
                .post()
        .then()
                .spec(responseSpec)
                .log().body()
                .extract().path("data.id");

        System.out.println("Teste ID: " + id);

    }

    @Test
    public void deletaContato(){
        given()
                .spec(requestSpec)
        .when()
                .delete("contacts/"+1788)
        .then()
                .statusCode(204);
    }

}
