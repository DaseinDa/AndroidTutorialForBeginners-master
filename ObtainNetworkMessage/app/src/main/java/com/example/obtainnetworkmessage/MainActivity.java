package com.example.obtainnetworkmessage;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ProgressDialog dialog;
    private final String CITY_PATH="http://192.168.1.102:8080/Web_ListView_Json/CityAction?type=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(MainActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("loading...");
        listView=(ListView) this.findViewById(R.id.listView1);
        new MyTask().execute(CITY_PATH);

    }

    class MyTask extends AsyncTask<String, Void, List<String>>{

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<String> doInBackground(String... params) {
            // TODO Auto-generated method stub
            String jsonString=HttpUtils.sendPostMethod(params[0], "utf-8");
            List<String> list=JsonTools.parseList(jsonString);
            return list;
        }

        @Override
        protected void onPostExecute(List<String> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, result);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();//一旦适配器有数据，直接通知listView更新
            dialog.dismiss();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}