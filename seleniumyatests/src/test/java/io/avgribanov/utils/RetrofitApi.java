package io.avgribanov.utils;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.Map;


public class RetrofitApi {

    @Test
    public void ApiTestOfferRetro() throws Exception {

        ApiConfigs cfg = ConfigFactory.create(ApiConfigs.class);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://autoru-api-01-sas.test.vertis.yandex.net:2600")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);

        Call<Map<String, Object>> user = service.getUser(
                cfg.category(),
                cfg.offerId(),
                cfg.xauthorization(),
                cfg.xsessionid());

        Response<Map<String, Object>> execute = user.execute();
        Map<String, Object> body = execute.body();
        String status = (String) body.get("status");
        Assert.assertEquals("SUCCESS", status);
    }

    public interface Api {

        @GET("/1.0/user/offers/{category}/{offerId}")
        Call<Map<String, Object>> getUser(@Path("category") String category, @Path("offerId") String offerId, @Header("x-authorization") String authorization, @Header("x-session-id") String sessionid);

    }
}
