package com.winchester.retrofit.example.http;

public interface RetrofitCallback<T> {
    void onSuccess(int code, T receivedData);

    void onFailure(int code);
}
