/*package io.avgribanov.utils;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;

import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

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
                    .then()
                    .statusCode(200);
                    //.assertThat()
                    //.body("placement");
        }


    }
*/