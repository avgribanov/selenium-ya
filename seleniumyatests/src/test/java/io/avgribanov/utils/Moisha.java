package io.avgribanov.utils;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Moisha {


        @Test
        public void ApiTestOffer() {

            ApiConfigs cfg = ConfigFactory.create(ApiConfigs.class);

            given()
    //                .header("service", cfg.service())
                    .pathParam("service", "autoru")
                    .when()
                    .get("http://moisha-api-01-sas.test.vertis.yandex.net:34410/api/1.x/service/{service}/products")
                   // .post("http://moisha-api-01-sas.test.vertis.yandex.net:34410/api/1.x/service/{service}/price")
                    //.body(rrr)
                    .then()
                    .statusCode(200);
                    //.assertThat()
                    //.body("status", equalTo("SUCCESS"));
        }


    }
