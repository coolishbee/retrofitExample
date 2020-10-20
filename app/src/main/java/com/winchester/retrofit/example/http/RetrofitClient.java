package com.winchester.retrofit.example.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import androidx.annotation.NonNull;

import com.winchester.retrofit.example.http.asyncTask.HttpPacketType;
import com.winchester.retrofit.example.http.asyncTask.PacketListener;
import com.winchester.retrofit.example.http.asyncTask.SendPacketTask;
import com.winchester.retrofit.example.http.responseBody.RespCarrier;
import com.winchester.retrofit.example.http.responseBody.RespCarrierTracks;
import com.winchester.retrofit.example.http.responseBody.RespPacket;

import retrofit2.Call;
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


    /**
     * @deprecated legacy system
     */
    /**
     * receive Packet Listener
     */
    private PacketListener packetListener = new PacketListener() {
        @Override
        public void onSuccess(int code,
                              RespPacket receivedData,
                              @NonNull HttpPacketType packetType) {
            switch (packetType)
            {
                case RESPONSE_CARRIERS:
                    break;
                case RESPONSE_CARRIERS_TRACKS:
                    RespCarrierTracks respData = (RespCarrierTracks)receivedData;
                    if(respData != null)
                    {
                        Log.d("RetrofitClient", respData.from.toString());
                        Log.d("RetrofitClient", respData.to.toString());
                        Log.d("RetrofitClient", respData.state.toString());
                        for (RespCarrierTracks.Progress item : respData.progresses)
                        {
                            Log.d("RetrofitClient", item.time);
                            Log.d("RetrofitClient", item.location.name);
                            Log.d("RetrofitClient", item.status.toString());
                            Log.d("RetrofitClient", item.description);
                        }
                        Log.d("RetrofitClient", respData.carrier.toString());
                    }
                    break;
            }
        }

        @Override
        public void onFailure(int code, String Msg) {
            Log.d("RetrofitClient", String.valueOf(code));
        }
    };

    public void sendPacketAsyncTask(Activity activity, String c_id, long t_id) {

        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("sendPacketServerTime...");

        Call<RespCarrierTracks> call = apiService.getCarriersTracks(c_id, t_id);
        SendPacketTask sendTask = new SendPacketTask(
                progressDialog,
                packetListener,
                HttpPacketType.RESPONSE_CARRIERS_TRACKS,
                null);
        sendTask.execute(call);
    }

}
