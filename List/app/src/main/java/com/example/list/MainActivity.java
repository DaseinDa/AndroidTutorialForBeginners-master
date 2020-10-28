package com.example.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.example.list.MyAdapter;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "hhhh";

    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
         /*
        Resources resourse = this.getResources();
        String[] data1 = resourse.getStringArray(R.array.rate1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data1
        );
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
*/



         /*
        ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemTitle","Rate:  "+i);
            map.put("ItemDetail","detail" +i);
            listItems.add(map);
        }
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,
                listItems, R.layout.list_item,
                new String[]{"ItemTitle", "ItemDetail"},
                new int[]{R.id.itemTitle, R.id.itemDetail}

                );
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(listItemAdapter);
*/


        ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();
        for (int i=0;i<10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemTitle","Rate:"+i);
            map.put("ItemDetail","detail"+i);
            listItems.add(map);
        }
        MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item,listItems);
        ListView listView = (ListView) findViewById(R.id.list_view);




        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) findViewById(R.id.list_view);
                Object itemAtPosition = listView.getItemAtPosition(position);
                HashMap<String,String> map = (HashMap<String, String>) itemAtPosition;
                String titleStr = map.get("ItemTitle");
                String detailStr = map.get("ItemDetail");
                Log.i(TAG,"onItemClick: titleStr=" + titleStr);
                Log.i(TAG,"onItemClick: detailStr" +detailStr);

                TextView title = (TextView) view.findViewById(R.id.itemTitle);
                TextView detail = (TextView) view.findViewById(R.id.itemDetail);
                String title2 = String.valueOf(title.getText());
                String detail2 = String.valueOf(detail.getText());
                Log.i(TAG,"onItemClick: title2=" +title2);
                Log.i(TAG,"onItemClick: detail2=" +detail2);

            }

        });




        final Handler handler = new Handler() {

            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    //do something

                }
                super.handleMessage(msg);
            }
        };

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            int count = 0;
            @Override
            public void run() {

                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                message.arg1 = count++;

            }
        };

        //主线程中调用：
        timer.schedule(timerTask, 10, 99999999);//延时1s，每隔500毫秒执行一次run方法


        // private String[] data = {"肖申克的救赎", "这个杀手不太冷", "霸王别姬", "泰坦尼克号", "瓦力", "三傻大闹宝莱坞", "放牛班的春天", "千与千寻", "鬼子来了", "星际穿越"};
    }




    public class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                setContentView(R.layout.activity_main);
                Resources resourse = getResources();
                String[] data2 = resourse.getStringArray(R.array.rate2);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.simple_list_item_1, data2
                );

                ListView listView2 = (ListView) findViewById(R.id.list_view);
                listView2.setAdapter(adapter2);

            }
        }
    }

}