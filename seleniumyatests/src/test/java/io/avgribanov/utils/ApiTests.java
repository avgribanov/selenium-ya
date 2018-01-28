package io.avgribanov.utils;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiTests {

    @Test
    public void ApiTestOffer() {

        ApiConfigs cfg = ConfigFactory.create(ApiConfigs.class);

        given()
                .header("x-authorization", cfg.xauthorization())
                .header("x-session-id", cfg.xsessionid())
                .when()
                .get("http://autoru-api-01-sas.test.vertis.yandex.net:2600/1.0/user/offers/cars/1068576562-348fbf")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("SUCCESS"));
        }
}
