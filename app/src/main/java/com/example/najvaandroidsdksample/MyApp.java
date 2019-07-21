package com.example.najvaandroidsdksample;

import android.app.Application;
import android.util.Log;

import com.najva.najvasdk.Class.Najva;
import com.najva.najvasdk.Class.NajvaJsonDataListener;

public class MyApp extends Application {
    private String TAG = "Application: ";
    @Override
    public void onCreate() {
        super.onCreate();
        Najva.setJsonDataListener(new NajvaJsonDataListener() {
            @Override
            public void onReceiveJson(String jsonString) {
                Log.i(TAG, "onReceiveJson: " + jsonString);

                // handel data
            }
        });
    }
}
