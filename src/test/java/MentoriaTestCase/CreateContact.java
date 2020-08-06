package MentoriaTestCase;

import Models.CreateUserModel;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import java.util.Scanner;

import static io.restassured.RestAssured.given;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateContact {

    Scanner numero = new Scanner(System.in);
    private static String id;
    private static RequestSpecification requestSpecList;
    private static RequestSpecification requestSpecPost;
    private static RequestSpecification requestSpecDelete;
    private static ResponseSpecification responseSpec;
    private static final CreateUserModel createUserModel = new CreateUserModel();

    @BeforeEach
    public void setUp(){

        requestSpecList = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        requestSpecPost = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts")
                .setBody(createUserModel)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        requestSpecDelete = new RequestSpecBuilder()
                .setBaseUri("https://api-de-tarefas.herokuapp.com")
                .setBasePath("/contacts/"+id)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/vnd.tasksmanager.v2")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .build();

    }

    @Test
    public void healthCheckTest(){
        given()
        .when()
                .get("https://api-de-tarefas.herokuapp.com/contacts")
        .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void listaContatoJson(){
        given()
                .spec(requestSpecList)
        .when()
                .get()
        .then()
                .log().body()
//                .spec(responseSpec)
                .statusCode(200);
    }

    @Test
    public void procuradoPorIdListaContatoJson(){
//        System.out.println("Digite o numero ID:");
//        int x = numero.nextInt();
        given()
                .spec(requestSpecList)
        .when()
                .get("/"+1600)
        .then()
                .log().body()
//                .spec(responseSpec)
                .statusCode(200);
    }

    @Test
    public void deleteContato(){
        given()
                .spec(requestSpecList)
        .when()
                .delete("/"+1602)
        .then()
                .statusCode(204);

        System.out.println("Deletou");
    }

    @Test
    @Order(1)
    public void criarContatoJson(){
        id = given()
                .spec(requestSpecPost)
            .when()
                .post()
            .then()
                .log().body()
                .spec(responseSpec)
                .extract().path("data.id");

        System.out.println(id);
    }

    @Test
    @Order(2)
    public void deleteContatoJson(){
        given()
                .spec(requestSpecDelete)
        .when()
                .delete()
        .then()
                .statusCode(204);

        System.out.println("Deletou");
    }

}
