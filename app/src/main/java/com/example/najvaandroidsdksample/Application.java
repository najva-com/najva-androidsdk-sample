package com.example.najvaandroidsdksample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.najva.sdk.Najva;
import com.najva.sdk.NajvaClient;
import com.najva.sdk.NajvaConfiguration;
import com.najva.sdk.NotificationClickListener;
import com.najva.sdk.NotificationReceiveListener;

public class Application extends android.app.Application {
    private static String TAG = "Application";

    @Override
    public void onCreate() {
        super.onCreate();

        NajvaClient.getInstance().setLogEnabled(true);
        NajvaClient.configuration.setNotificationClickListener(s -> Log.d(TAG, "onClickNotification: " + s));

        NajvaClient.configuration.setReceiveNotificationListener(notificationId -> Log.d(TAG, "onReceiveNotification: " + notificationId));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(String id, String name, int importance){
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),null);
        channel.setShowBadge(true);
        channel.setBypassDnd(false);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }
}
