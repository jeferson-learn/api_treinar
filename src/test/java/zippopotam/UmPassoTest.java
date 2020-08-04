package zippopotam;

import io.restassured.specification.Argument;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UmPassoTest {

    @Test
    public void getCode90210_checkName(){
        given()
        .when()
                .get("http://api.zippopotam.us/us/90210")
        .then()
                .body("places[0].'place name'", equalTo("Beverly Hills"));
    }

}
