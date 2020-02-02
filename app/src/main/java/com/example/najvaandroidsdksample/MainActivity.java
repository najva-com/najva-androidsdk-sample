package com.example.najvaandroidsdksample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Type;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.najva.sdk.Najva;
import com.najva.sdk.NajvaJsonDataListener;
import com.najva.sdk.UserSubscriptionListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final int PICK_IMAGE = 1;

    ImageView mMainImage;
    ImageView mBlurImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainImage = findViewById(R.id.main_image);
        mBlurImage = findViewById(R.id.blured_image);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Not Implemented yet.", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.pick) {
            selectImage();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            Uri uri = data.getData();
            if (uri != null) {
                Bitmap bitmap = uriToBitmap(uri);
                handleBitmap(bitmap);
            } else {
                Log.d(TAG, "onActivityResult: uri is null");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private Bitmap uriToBitmap(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleBitmap(Bitmap bitmap) {
        mMainImage.setImageBitmap(bitmap);
        Bitmap copy = bitmap.copy(Bitmap.Config.RGB_565, true);
        displayBlurredImage(copy);
    }

    private void displayBlurredImage(Bitmap copy) {
        Bitmap newBitmap = blurBitmap(copy);

        mBlurImage.setImageBitmap(newBitmap);

    }

    public Bitmap blurBitmap(Bitmap bitmap) {
        Bitmap bmBlur;
        Convolution convolution = new Convolution();

        bmBlur = convolution.convBitmap(bitmap);

        return bitmap;
    }
}
