package com.example.panacloud.myservicedemo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;


public class MainActivity extends Activity {
    Button mButtonStart,mButtonStop;
    RelativeLayout relativeLayout;
    ImageView imageView;
    private ActionBar actionBar;
    static Bitmap bitmapScreenShot;
    boolean b;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonStart = (Button) findViewById(R.id.button);
        mButtonStop = (Button) findViewById(R.id.button2);
        imageView = (ImageView) findViewById(R.id.imageView2);
        actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.mall_actionbar_layout);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        mp = new MediaPlayer();


        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
//                b=true;
//                startActivity(new Intent(getApplicationContext(), ScreenShotActivity.class));
            }
        });

        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b=false;
                startActivity(new Intent(getApplicationContext(), ScreenShotActivity.class));
            }
        });

//        imageView.setImageBitmap(bitmapScreenShot);

        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource("http://stream.wmbr.org:8000/med");
            mp.prepareAsync();
        } catch (IOException e) {

        }

    }


    @Override
    protected void onPause() {

        if(b){
            relativeLayout.setDrawingCacheEnabled(true);
            bitmapScreenShot = Bitmap.createBitmap(relativeLayout.getDrawingCache());
        }
        else{
            actionBar.getCustomView().setDrawingCacheEnabled(true);
            bitmapScreenShot = Bitmap.createBitmap(actionBar.getCustomView().getDrawingCache());
        }


        super.onPause();
    }
}
