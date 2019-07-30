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



        Najva.initialize(this, YOUR_CAMPAIGN_ID_GOES_HERE, YOUR_WEBSITE_ID_GOES_HERE, YOUR_API_KEY_GOES_HERE);

        Najva.setUserHandler(new NajvaUserHandler(){
            @Override
            public void najvaUserSubscribed(String token) {
                Log.i(TAG, "najvaUserSubscribed: " + token);

                //handel token
            }
        });


    }
}
