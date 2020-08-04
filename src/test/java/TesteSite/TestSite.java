package TesteSite;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class TestSite {

    @Test
    public void healthCheckTest(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/usersXML")
                .then()
                .log().body()
                .statusCode(200);
    }

}
