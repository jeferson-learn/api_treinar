package zippopotam;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TresPassoTest {

    @Test
    public void getCode90210_checkPlaceName_ResponseBody_BeverlyHills(){
        given()
        .when()
                .get("http://api.zippopotam.us/us/90210")
        .then()
                .body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void getCode12345_checkPlaceName_ResponseBody_Schenectady(){
        given()
                .when()
        .get("http://api.zippopotam.us/us/12345")
        .then()
                .body("places[0].'place name'", equalTo("Schenectady"));
    }

    @Test
    public void getCodeB2R_checkPlaceName_ResponseBody_Waverley(){
        given()
        .when()
        .get("http://api.zippopotam.us/ca/B2R")
        .then()
                .body("places[0].'place name'", equalTo("Waverley"));
    }


}
