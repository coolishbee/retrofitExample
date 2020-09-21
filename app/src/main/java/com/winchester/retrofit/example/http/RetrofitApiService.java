package com.winchester.retrofit.example.http;

import com.google.gson.JsonObject;
import com.winchester.retrofit.example.http.responseBody.RespCarrier;
import com.winchester.retrofit.example.http.responseBody.RespCarrierTracks;
import com.winchester.retrofit.example.http.responseBody.RespSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApiService {



    @GET("/carriers")
    Call<List<RespCarrier>> getCarriers();

    @GET("/carriers/{carrier_id}/tracks/{track_id}")
    Call<RespCarrierTracks> getCarriersTracks(@Path("carrier_id") String carrier_id,
                                              @Path("track_id") long track_id);

}
