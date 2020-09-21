package com.winchester.retrofit.example.http.asyncTask;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.winchester.retrofit.example.http.responseBody.RespPacket;

public interface PacketListener {
    void onSuccess(int code, RespPacket receivedData,
                   @NonNull final HttpPacketType packetType);

    void onFailure(int code, String Msg);
}
