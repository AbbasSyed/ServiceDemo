package com.example.panacloud.myservicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


public class ScreenShotActivity extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);

        getActionBar().hide();
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(MainActivity.bitmapScreenShot);


    }

}
