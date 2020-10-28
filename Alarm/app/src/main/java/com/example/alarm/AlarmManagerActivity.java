package com.example.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jph.simple.service.AlarmService;

import java.util.Calendar;

/**
 * Created by wyb on 2018/2/24.
 * <p>
 * FLAG_CANCEL_CURRENT:如果当前系统中已经存在一个相同的PendingIntent对象，那么就将先将已有的PendingIntent取消，然后重新生成一个PendingIntent对象。
 * <p>
 * FLAG_NO_CREATE:如果当前系统中不存在相同的PendingIntent对象，系统将不会创建该PendingIntent对象而是直接返回null。
 * <p>
 * FLAG_ONE_SHOT:该PendingIntent只作用一次。在该PendingIntent对象通过send()方法触发过后，PendingIntent将自动调用cancel()进行销毁，那么如果你再调用send()方法的话，系统将会返回一个SendIntentException。
 * <p>
 * FLAG_UPDATE_CURRENT:如果系统中有一个和你描述的PendingIntent对等的PendingInent，那么系统将使用该PendingIntent对象，但是会使用新的Intent来更新之前PendingIntent中的Intent对象数据，例如更新Intent中的Extras
 */

public class AlarmManagerActivity extends Activity {

    private AlarmManager alManager;
    private AlarmReceiver alarmReceiver;
    /******receiver_action*******/
    public final static String ALARM_RECEIVER_ACTION = "receiver_alarm_action";
    /******activity_action*******/
    public final static String ALARM_ACTIVITY_ACTION = "activity_alarm_action";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmmanager_layout);
        alManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//        System.out.println("----getLocalClassName---------" + getLocalClassName());
//        System.out.println("----getPackageName---------" + getPackageName());
//        System.out.println("----getComponentName---------" + getComponentName());
//        System.out.println("----getCallingPackage---------" + getCallingPackage());
//        System.out.println("----getTitle---------" + getTitle());

        /***********alarmManager广播注册*************/
        alarmReceiver = new AlarmReceiver();
        registerReceiver(alarmReceiver,new IntentFilter(ALARM_RECEIVER_ACTION));
    }

    /**
     * 开启定时任务
     * @param view
     */
    public void startAlarmTask(View view) {
        Log.d(getLocalClassName(), "--------------you clicked me!");
        //showToastByCalendar();
        //showToastByMillis();
        //showToastReceiver();
        showToastActivity();

    }


    /**
     * service
     * 定时到秒
     */
    void showToastByMillis() {
        Intent intent = new Intent(this, AlarmService.class);
        intent.setAction(AlarmService.ACTION_ALARM);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT < 19) {
            alManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        } else {
            alManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        }
    }

    /**
     * serice
     * 定时到年月日
     */
    void showToastByCalendar() {
        //定时时间为2018年2月24日15点25分0秒
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 24);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 25);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, AlarmService.class);
        intent.setAction(AlarmService.ACTION_ALARM);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT < 19) {
            alManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }


    /**
     * BroadcastReceiver
     * 接收定时任务
     */
    void showToastReceiver(){

        Intent intent = new Intent();
        intent.setAction(ALARM_RECEIVER_ACTION);
        intent.putExtra("AlARMTASK","I am AlarmManager Timer Task by BroadcastReceiver");
        //sendBroadcast(intent); 这里不用写，否则会接收到两次
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT < 19) {
            alManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        } else {
            alManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        }

    }


    /**
     * activity
     * 接收定时任务
     */
    void showToastActivity(){
        Intent intent = new Intent(this,ReceiverAlarmTaskActivity.class);
        intent.setAction(ALARM_ACTIVITY_ACTION);
        intent.putExtra("AlARMTASK","I am AlarmManager Timer Task by Activity");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT < 19) {
            alManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        } else {
            alManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        }
    }



    /********************************************广播接收定时任务************************************************************/
    public class AlarmReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals(ALARM_RECEIVER_ACTION)){
                return;
            }
            Toast.makeText(AlarmManagerActivity.this,intent.getStringExtra("AlARMTASK"),Integer.valueOf(1)).show();

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alarmReceiver!=null){
            unregisterReceiver(alarmReceiver);
        }
    }
}
