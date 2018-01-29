package io.avgribanov.utils;

import io.restassured.path.json.JsonPath;
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

        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        assertThat(status).isEqualTo("SUCCESS");
        String wheel = jsonPathEvaluator.get("offer.car_info.steering_wheel");
        assertThat(wheel).isEqualTo("LEFT");
    }
}
