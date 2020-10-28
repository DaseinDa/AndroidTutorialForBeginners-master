package com.example.alarm;


//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiverAlarmTaskActivity extends AppCompatActivity {
    private TextView alarmTaskActivity_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_alarm_task);
        alarmTaskActivity_tv = findViewById(R.id.alarmTaskActivity_tv);
        try {
            boolean actionIs = getIntent().getAction().equals(AlarmManagerActivity.ALARM_ACTIVITY_ACTION);
            if (actionIs){
                String taskString = getIntent().getStringExtra("AlARMTASK");
                Toast.makeText(this,taskString,Integer.valueOf(1)).show();
                alarmTaskActivity_tv.setText(taskString);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
