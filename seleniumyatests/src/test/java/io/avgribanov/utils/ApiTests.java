package io.avgribanov.utils;

import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiTests {

    @Test
    public void ApiTestOffer() {

        ApiConfigs cfg = ConfigFactory.create(ApiConfigs.class);

        Response response = given()
                .headers("x-authorization", cfg.xauthorization(), "x-session-id", cfg.xsessionid())
                .pathParams("category", cfg.category(), "offerId", cfg.offerId())
                .when()
                .get("http://autoru-api-01-sas.test.vertis.yandex.net:2600/1.0/user/offers/{category}/{offerId}");
               // .then()
               // .statusCode(200);

        String status = response.jsonPath().get("status");
        assertThat(status).isEqualTo("SUCCESS");
        //String wheel = response.jsonPath().get("offer.car_info.steering_wheel");
        //assertThat(wheel).isEqualTo("LEFT");
    }
}
