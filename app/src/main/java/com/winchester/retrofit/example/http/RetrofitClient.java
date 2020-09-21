package com.winchester.retrofit.example.http;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public RetrofitApiService apiService;
    private static Retrofit retrofit;


    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(RetrofitUtils.API_SERVER_Base_URL);
    }

    public static RetrofitClient get() {
        return SingletonHolder.INSTANCE;
    }

    public RetrofitClient(@NonNull final String apiBaseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitUtils.getUnsafeOkHttpClient().build())
                .build();

        apiService = create(RetrofitApiService.class);
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    private  <T> T create(final Class<T> service) {
        if(service == null) {
            throw new RuntimeException("Api service is null");
        }
        return retrofit.create(service);
    }



}
