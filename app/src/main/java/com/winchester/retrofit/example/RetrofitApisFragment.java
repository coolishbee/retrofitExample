package com.winchester.retrofit.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.winchester.retrofit.example.http.RetrofitCallback;
import com.winchester.retrofit.example.http.RetrofitClient;
import com.winchester.retrofit.example.http.responseBody.RespCarrier;
import com.winchester.retrofit.example.http.responseBody.RespCarrierTracks;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitApisFragment extends Fragment {

    private static final String TAG = "retrofitExample";

    @Nullable
    @BindView(R.id.log)
    TextView logView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_api, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void addLog(@NonNull String logText) {
        Log.d(TAG, logText);
        if(logView != null) {
            logView.setText(logView.getText() + "\n" + logText);
        }
    }

    @OnClick(R.id.clear_log_btn)
    void clear() {
        if(logView != null)
            logView.setText("");
    }

    @OnClick(R.id.carriers_btn)
    public void onCarriersBtnClick() {

        Call<List<RespCarrier>> call = RetrofitClient.get().apiService.getCarriers();
        call.enqueue(new Callback<List<RespCarrier>>() {
            @Override
            public void onResponse(@NotNull Call<List<RespCarrier>> call,
                                   @NotNull Response<List<RespCarrier>> response) {
                if(response.body() != null)
                {
                    for(RespCarrier item: response.body()) {
                        addLog(item.toString());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<RespCarrier>> call, @NotNull Throwable t) {

            }
        });

    }

    @OnClick(R.id.carriersV2_btn)
    public void onCarriersV2BtnClick() {

    }

    @OnClick(R.id.tracks_btn)
    public void onTracksBtnClick() {

        long track_id = 1111111111111L;

        Call<RespCarrierTracks> tracksCall = RetrofitClient.get().
                apiService.getCarriersTracks("kr.epost", track_id);
        tracksCall.enqueue(new Callback<RespCarrierTracks>() {
            @Override
            public void onResponse(@NotNull Call<RespCarrierTracks> call,
                                   @NotNull Response<RespCarrierTracks> response) {
                RespCarrierTracks respData = response.body();
                if(respData != null)
                {
                    addLog(respData.from.toString());
                    addLog(respData.to.toString());
                    addLog(respData.state.toString());
                    for (RespCarrierTracks.Progress item : respData.progresses)
                    {
                        addLog(item.time);
                        addLog(item.location.name);
                        addLog(item.status.toString());
                        addLog(item.description);
                    }
                    addLog(respData.carrier.toString());
                }
            }

            @Override
            public void onFailure(@NotNull Call<RespCarrierTracks> call, @NotNull Throwable t) {
                call.cancel();
            }
        });

    }


}