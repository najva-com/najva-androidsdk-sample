package com.example.najvaandroidsdksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.najva.sdk.Najva;
import com.najva.sdk.NajvaJsonDataListener;
import com.najva.sdk.UserSubscriptionListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Najva.initialize(this);
        Najva.setUserSubscriptionListener(new UserSubscriptionListener() {
            @Override
            public void onUserSubscribed(String s) {
                Log.i(TAG, "onUserSubscribed: " + s);
            }
        });
        Log.i(TAG, "onCreate: " + Najva.getSubscribedToken(this));

        Najva.setNajvaJsonDataListener(new NajvaJsonDataListener() {
            @Override
            public void onReceiveJson(String s) {
                Log.i(TAG, "onReceiveJson: " + s);
            }
        });

        Najva.getCachedJsonData(this);

    }
}
