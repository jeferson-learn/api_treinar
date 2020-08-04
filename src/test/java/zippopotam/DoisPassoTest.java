package zippopotam;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DoisPassoTest {

    @Test
    public void getCode90210_checkStatus(){
        given()
        .when()
                .get("http://api.zippopotam.us/us/90210")
        .then()
                .statusCode(200);
    }

    @Test
    public void getCode90210_checkContentType() {
        given()
        .when()
                .get("http://api.zippopotam.us/us/90210")
        .then()
                .contentType(ContentType.JSON);
//                .contentType("application/json") /* pode ser tambem */
//                .contentType(ContentType.XML); /* ERRO pq nao é tipo XML */
    }

    @Test
    public void getCode90210_checkLog(){
        given()
                .log().all()
        .when()
                .get("http://api.zippopotam.us/us/90210")
        .then()
                .log().body();
    }

    @Test
    public void getCode90210_checkPlaceName_ResponseBody(){
        given()
        .when()
                .get("http://api.zippopotam.us/us/90210")
        .then()
                .body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void getCode90210_checkState_ResponseBody(){
        given()
        .when()
                .get("http://api.zippopotam.us/us/90210")
        .then()
                .body("places[0].state", equalTo("California"));
    }
}
