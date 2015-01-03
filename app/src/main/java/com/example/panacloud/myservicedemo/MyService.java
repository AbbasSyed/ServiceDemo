package com.example.panacloud.myservicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import java.io.IOException;

/**
 * Created by panacloud on 12/28/14.
 */

public class MyService extends Service {
    private MediaPlayer mp; //MediaPlayer used to play streamed music
    String URL;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override //the Call to startService() runs the onStartCommand from where the Service begins its process.
    public int onStartCommand(Intent intent, int flags, int startId) {

        //retrieving data from an Intent which was passed through the call to startService
        URL = intent.getStringExtra("URL Stream");




        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(URL);
            mp.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        Notification notification = new Notification(R.drawable.ic_launcher, "Service Running",System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.setLatestEventInfo(this, "Service is Running",
                URL, pendingIntent);
        //A known bug in Android v4.4 KitKat requires the Service to show up in the Foreground (System Notification bar)
        startForeground(Notification.FLAG_ONLY_ALERT_ONCE, notification);



        //A constant provided from the Services class which tells the Android System
        //how to proceed with the service in case it is killed or restarted.
        return Service.START_REDELIVER_INTENT;
    }

    @Override //used to bind the Application to the Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
        mp=null;

        //stopSelf() is used when the service has completed its process and needs to stop by itself without its Activity.
//        stopSelf();
    }
}
