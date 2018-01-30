package io.avgribanov.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.Map;

public interface Api {

    @GET("/1.0/user/offers/{category}/1068576562-348fbf")
    Call<Map<String, Object>> getUser(@Path("category") String category, @Header("x-authorization") String authorization, @Header("x-session-id") String sessionid);

}