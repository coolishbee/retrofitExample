package com.winchester.retrofit.example.http.asyncTask;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.winchester.retrofit.example.http.responseBody.RespPacket;

import java.io.IOException;
import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Response;

public class SendPacketTask extends AsyncTask<Call, Void, Response<RespPacket>> {

    private WeakReference<ProgressDialog> progressDialog;
    private NextAction nextAction;

    @Nullable
    private PacketListener packetListener;
    private HttpPacketType packetType;


    public SendPacketTask(ProgressDialog progressDialog,
                   @Nullable PacketListener packetListener,
                   @NonNull final HttpPacketType type,
                   NextAction action) {

        this.progressDialog = new WeakReference<>(progressDialog);
        this.packetListener = packetListener;
        this.nextAction = action;
        this.packetType = type;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.get().setCancelable(false);
        progressDialog.get().show();
    }

    @Override
    protected Response<RespPacket> doInBackground(Call... params) {

        try{
            Call<RespPacket> call = params[0];
            Response<RespPacket> response = call.execute();
            return response;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<RespPacket> response) {

        if(packetListener != null && response != null)
        {
            if( response.isSuccessful() )
            {
                packetListener.onSuccess(response.code(), response.body(), packetType);
            }else {
                packetListener.onFailure(response.code(), "err msg");
            }
        }

        if(progressDialog.get() != null && progressDialog.get().isShowing())
            progressDialog.get().dismiss();

        if(nextAction != null)
            nextAction.run();
    }

    @FunctionalInterface
    public interface NextAction {
        void run();
    }
}
