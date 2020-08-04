package TheCatApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Cadastro extends MassaDeDados {

    @BeforeAll
    public static void urlBase(){
        RestAssured.baseURI = "https://api.thecatapi.com/v1/";
    }

    @Test
    public void cadastro(){

        Response response =
        given()
                .contentType("application/json")
                .body(corpoCadastro)
        .when()
                .post(urlCadastro);

        validacao(response);

    }

//    @Test
    public void votacao(){

        Response response =
        given()
                .contentType("application/json")
                .body(votacao)
        .when()
                .post("votes/");

        validacao(response);

        String id = response.jsonPath().getString("id");

        vote_id = id;

        System.out.println("Retorno ID: " + id);

    }

    @Test
    public void deleteVotacao(){

        votacao();

        Response response =
                given()
                    .contentType("application/json")
                    .header("x-api-key","0faaff46-6e2f-4eb8-8079-9b7c0317f5d2")
                    .pathParam("vote_id", vote_id)
                .when()
                    .delete("votes/{vote_id}");

        validacao(response);

    }

    @Test
    public void favoritaDesfavorita(){
        favorita();
        desfavorita();
    }

    private void favorita() {
        Response response =
        given()
                .contentType("application/json")
                .header("x-api-key", "0faaff46-6e2f-4eb8-8079-9b7c0317f5d2")
                .body(corpoFavorita)
        .when()
                .post("favourites");

        String id = response.jsonPath().getString("id");

        vote_id = id;

        validacao(response);

    }

    private void desfavorita() {
        Response response =
                given()
                        .contentType("application/json")
                        .header("x-api-key", "0faaff46-6e2f-4eb8-8079-9b7c0317f5d2")
                        .pathParam("favourite_id", vote_id)
                .when()
                        .delete("favourites/{favourite_id}");


        validacao(response);

    }

    public void validacao(Response response){
        response.then()
                .body("message", containsString("SUCCESS"));
        System.out.println("Retorno da API: " + response.body().asString());
        System.out.println("===============================================");
    }

}
