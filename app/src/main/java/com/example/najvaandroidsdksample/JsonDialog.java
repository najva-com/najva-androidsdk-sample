package com.example.najvaandroidsdksample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDialog extends AlertDialog implements View.OnClickListener {
    private JSONObject jsonObject;

    protected JsonDialog(Context context, String json) {
        super(context);
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.dialog_json);
        TextView title = findViewById(R.id.text_title);
        title.setText(getTitle());
        TextView description = findViewById(R.id.text_description);
        description.setText(getDescription());
        Button action = findViewById(R.id.action);
        action.setOnClickListener(this);
        action.setText(getButtonText());
        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
    }

    private String getTitle() {
        try {
            return jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getDescription() {
        try {
            return jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getButtonText() {
        try {
            return jsonObject.getString("button_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "start";
    }

    private String getUrl() {
        try {
            return jsonObject.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.action:
                String url = getUrl();
                if (url != null) {
                    dismiss();
                    openUrl(url);
                }
                break;
        }
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        getContext().startActivity(intent);
    }
}
