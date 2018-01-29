/*package io.avgribanov.utils;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;
import retrofit.client.Response;
import retrofit.http.GET;

public class RetrofitApi {

    @Test
    public void ApiTestOfferRetro() {
        ApiConfigs cfg = ConfigFactory.create(ApiConfigs.class);

        @Headers({
                "x-authorization: cfg.xauthorization()",
                "x-session-id: cfg.xsessionid()"
        })
        @GET("/1.0/user/offers/cars/1068576562-348fbf")
        User getUser(@Path("username") String username);
    }

    */
