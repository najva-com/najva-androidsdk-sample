package com.example.najvaandroidsdksample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.najva.sdk.NajvaClient;
import com.najva.sdk.UserSubscriptionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";


    int id = 10;
    NotificationManager manager;

    Application getNajvaApplication() {
        return (Application) getApplication();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        NajvaClient.configuration.setUserSubscriptionListener(new UserSubscriptionListener() {
            @Override
            public void onUserSubscribed(String token) {
                TextView tv = findViewById(R.id.textToken);
                tv.setText(NajvaClient.getInstance().getSubscribedToken());
            }
        });

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        TextView tv = findViewById(R.id.textToken);
        tv.setText(NajvaClient.getInstance().getSubscribedToken());

    }

    public Map<String, Object> getBasicData() {
        Map<String, Object> map = new HashMap<>();
        map.put("title", "title 2");
        map.put("body", "this is some description");
        map.put("onclick_action", "open-app");
        map.put("api_key", "8b84ad3a-3daa-4520-9adc-d7528ea95a54");
        map.put("url", "https://najva.com");

        JSONArray array = new JSONArray();
        array.put(NajvaClient.getInstance().getSubscribedToken());
        map.put("subscriber_tokens", array);

        return map;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Token aff3c68af97bf4608fdf28673ca26201ca027ed9");
        return map;
    }

    public void notificationLowPriority(View view) {
        Map<String, Object> data = getBasicData();

        data.put("priority", "normal");

        JsonObjectRequest request = new JsonObjectRequest(
                "https://app.najva.com/notification/api/v1/notifications/",
                (JSONObject) JSONObject.wrap(data),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                return MainActivity.this.getHeaders();
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    public void notificationHighPriority(View view) {
        Map<String, Object> data = getBasicData();
        data.put("priority", "high");
        Log.d("NotificationData", data.toString());
        JsonObjectRequest request = new JsonObjectRequest(
                "https://app.najva.com/notification/api/v1/notifications/",
                (JSONObject) JSONObject.wrap(data),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                return MainActivity.this.getHeaders();
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    public void notificationIcon(View view) {
        Map<String, Object> data = getBasicData();
        data.put("priority", "high");
        data.put("icon", "https://doc.najva.com/img/najva.png");
        JsonObjectRequest request = new JsonObjectRequest(
                "https://app.najva.com/notification/api/v1/notifications/",
                (JSONObject) JSONObject.wrap(data),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                return MainActivity.this.getHeaders();
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    public void notificationImage(View view) {
        Map<String, Object> data = getBasicData();
        data.put("priority", "high");
        data.put("icon", "https://doc.najva.com/img/najva.png");
        data.put("image", "https://doc.najva.com/img/najva.png");

        JsonObjectRequest request = new JsonObjectRequest(
                "https://app.najva.com/notification/api/v1/notifications/",
                (JSONObject) JSONObject.wrap(data),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                return MainActivity.this.getHeaders();
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    public void notificationBigText(View view) {
        Map<String, Object> data = getBasicData();
        data.put("priority", "high");
        data.put("body", "this is some very very long description that we hope it'll be displayed well in the notification bar in every android device :)");
        JsonObjectRequest request = new JsonObjectRequest(
                "https://app.najva.com/notification/api/v1/notifications/",
                (JSONObject) JSONObject.wrap(data),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                return MainActivity.this.getHeaders();
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
}
