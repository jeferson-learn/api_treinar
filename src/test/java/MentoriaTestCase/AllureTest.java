package MentoriaTestCase;

import com.sun.org.glassfish.gmbal.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Feature("Feature 1 (Classe)")
public class AllureTest {

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

    @Feature("Feature 1 (Metodo)")
    @DisplayName("Pesquisa com id 37")
    @Description("API e mostra listar do contato id 37")
    @Issue("https://apidetarefas.docs.apiary.io/#reference/0/contatos/listar-contatos")
    @Test
    public void listarContatoId37(){
        given()
                .spec(requestSpec)
        .when()
                .get("/37")
        .then()
                .log().body()
                .spec(responseSpec)
                .statusCode(200);
    }
}
