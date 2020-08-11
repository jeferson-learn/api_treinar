package TesteSite;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class TESTEAuthe {

    @Test
    public void AutenticacaoTipo1(){
        given()
        .when()
                .get("https://admin:senha@restapi.wcaquino.me/basicauth")
        .then()
                .log().body()
                .statusCode(200)
                .body("status",is("logado"));
    }

    @Test
    public void AutenticacaoTipo2(){
        given()
                .auth().basic("admin","senha")
        .when()
                .get("https://restapi.wcaquino.me/basicauth")
        .then()
                .log().body()
                .statusCode(200)
                .body("status",is("logado"));
    }

    @Test
    public void AutenticacaoTipo3(){
        given()
                .auth().preemptive().basic("admin","senha")
        .when()
                .get("https://restapi.wcaquino.me/basicauth2")
        .then()
                .log().body()
                .statusCode(200)
                .body("status",is("logado"));
    }

    @Test
    public void AutenticacaoToken(){
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "jeferson.lopes@dbserver.com.br");
        login.put("senha", "senha");

        String token =
        given()
                .log().all()
                .body(login)
                .contentType(ContentType.JSON)
        .when()
                .post("https://barrigarest.wcaquino.me/signin")
        .then()
                .log().all()
                .statusCode(200)
                .extract().path("token");

        System.out.println("Token: " + token);

        given()
                .log().all()
                .header("Authorization", "JWT " + token)
        .when()
                .get("https://barrigarest.wcaquino.me/contas")
        .then()
                .log().all()
                .statusCode(200)
                .body("name", hasItem("conta 1"));
    }

}
