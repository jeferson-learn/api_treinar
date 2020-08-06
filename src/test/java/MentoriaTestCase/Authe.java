package MentoriaTestCase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class Authe {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeEach
    public void setUp(){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://barrigarest.wcaquino.me/")
                .setBasePath("signin")
                .addHeader("Content-Type","application/json")
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }


    @Test
    public void athentication(){
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "jeferson.lopes@dbserver.com.br");
        login.put("senha", "senha");

        String token =
                given()
                        .spec(requestSpec)
                        .body(login)
                .when()
                        .post()
                .then()
                        .spec(responseSpec)
                        .log().body()
                        .extract().path("token");

        acessarConta(token);
    }

    public void acessarConta(String token){
        given()
                .header("Authorization", "JWT "+token)
        .when()
                .get("https://barrigarest.wcaquino.me/contas")
        .then()
                .log().body()
                .body("nome", hasItems("conta 1", "conta 2"));
    }

}
