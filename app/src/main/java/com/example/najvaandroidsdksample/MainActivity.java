package com.example.najvaandroidsdksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.najva.najvasdk.Class.Najva;
import com.najva.najvasdk.Class.NajvaJsonDataListener;
import com.najva.najvasdk.Class.NajvaUserHandler;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int campaignId = 1247756; // Available in the panel
        int websitenId = 6087;// Available in the panel
        String apiKey = "4647ae37-f35b-40fe-94d7-c27dea3b366e";// Available in the panel
        boolean isLocationEnable = true;// set true to get location of user

        Najva.initialize(this, campaignId, websitenId, apiKey, isLocationEnable);

        Najva.setUserHandler(new NajvaUserHandler(){
            @Override
            public void najvaUserSubscribed(String token) {
                Log.i(TAG, "najvaUserSubscribed: " + token);

                //handel token
            }
        });

        Najva.setJsonDataListener(new NajvaJsonDataListener() {
            @Override
            public void onReceiveJson(String jsonString) {
                Log.i(TAG, "onReceiveJson: " + jsonString);

                // handel data
            }
        });
    }
}
