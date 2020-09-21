package com.winchester.retrofit.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "retrofitExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hidden input key.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if (getSupportFragmentManager().getFragments().isEmpty()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new RetrofitApisFragment())
                    .commit();
        }
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//    }
}