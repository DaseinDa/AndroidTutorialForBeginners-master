package com.jph.simple.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class AlarmService extends Service {

    public static String ACTION_ALARM = "action_alarm";
    private Handler alarmHandler = new Handler(Looper.getMainLooper());

    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!intent.getAction().equals(ACTION_ALARM)){
            Log.d(getPackageName(),"--------no fitter action-------------");
        }

        alarmHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(getPackageName(),"---------I am coming");
                Toast.makeText(AlarmService.this,"我是闹钟",Toast.LENGTH_SHORT).show();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}