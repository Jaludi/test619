package com.example.android.greateapp.restPack;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Android on 6/6/2017.
 */

public interface RetrofitService {
    @GET("api")
    Call<RandomAPI> getRandomUser();
}
