package com.example.panacloud.myservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by panacloud on 12/26/14.
 */
public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}