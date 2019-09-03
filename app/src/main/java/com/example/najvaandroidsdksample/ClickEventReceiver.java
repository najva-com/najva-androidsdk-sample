package com.example.najvaandroidsdksample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.najva.sdk.Najva;

public class ClickEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NajvaReceiver", "onReceive: " + intent.getIntExtra(Najva.BUTTON_ID,-1) +
                ", " +
                intent.getStringExtra(Najva.MESSAGE_ID));
        // You can interactive with google analytics here
    }
}
