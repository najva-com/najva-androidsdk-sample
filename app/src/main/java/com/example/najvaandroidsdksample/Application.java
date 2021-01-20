package com.example.najvaandroidsdksample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.najva.sdk.NajvaClient;
import com.najva.sdk.NajvaConfiguration;
import com.najva.sdk.NotificationClickListener;
import com.najva.sdk.NotificationReceiveListener;

public class Application extends android.app.Application {
    private static String TAG = "Application";

    @Override
    public void onCreate() {
        super.onCreate();
        NajvaConfiguration configuration = new NajvaConfiguration();
        configuration.disableLocation();
        configuration.setFirebaseEnabled(false);
        configuration.setNotificationClickListener(new NotificationClickListener() {
            @Override
            public void onClickNotification(String notificationId, String buttonId) {
                Log.d(TAG, "onClickNotification: " + notificationId);
                Log.d(TAG, "onClickNotification: " + buttonId);
            }
        });

        configuration.setReceiveNotificationListener(new NotificationReceiveListener() {
            @Override
            public void onReceiveNotification(String notificationId) {
                Log.d(TAG, "onReceiveNotification: " + notificationId);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel("news", "News", NotificationCompat.PRIORITY_HIGH);
            createNotificationChannel("updates","Updates", NotificationCompat.PRIORITY_DEFAULT);
        }



        NajvaClient client = NajvaClient.getInstance(this, configuration);


        registerActivityLifecycleCallbacks(client);
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
