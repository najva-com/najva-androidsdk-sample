package com.example.najvaandroidsdksample;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.najva.sdk.NajvaClient;
import com.najva.sdk.NajvaConfiguration;
import com.najva.sdk.NajvaJsonDataListener;
import com.najva.sdk.UserSubscriptionListener;

import java.util.HashMap;
import java.util.Map;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NajvaConfiguration configuration = new NajvaConfiguration();
        configuration.disableLocation();
        configuration.setUserSubscriptionListener(new UserSubscriptionListener() {
            @Override
            public void onUserSubscribed(String token) {
                sendTokenToServer(token);
            }
        });
        configuration.setNajvaJsonDataListener(new NajvaJsonDataListener() {
            @Override
            public void onReceiveJson(String jsonString) {
                displayDialog(jsonString);
            }
        });

        NajvaClient client = new NajvaClient(this, configuration);
        client.getCachedJsonData();

        registerActivityLifecycleCallbacks(client);

        if (shouldUpdateToken()) {
            sendTokenToServer(client.getSubscribedToken());
        }
    }

    private void displayDialog(String jsonString) {
        new JsonDialog(this, jsonString).show();
    }

    private boolean shouldUpdateToken() {
        return getSharedPreferences("app", MODE_PRIVATE).getBoolean("update_token", false);
    }

    private void sendTokenToServer(String token) {
        if (token == null) return;

        String url = "https://google.com/generate_201";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final Map<String, String> post = new HashMap<>();
        post.put("token", token);
        requestQueue.add(new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Application", "token registered to server");
                disableUpdateToken();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Application", "error registering token to server");
                setUpdateTokenNextLaunch();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return post;
            }
        });
    }

    private void disableUpdateToken() {
        getSharedPreferences("app", MODE_PRIVATE).edit().putBoolean("update_token", false).apply();
    }

    private void setUpdateTokenNextLaunch() {
        getSharedPreferences("app", MODE_PRIVATE).edit().putBoolean("update_token", true).apply();
    }
}
