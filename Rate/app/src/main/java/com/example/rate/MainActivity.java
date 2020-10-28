package com.example.rate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText user;
    EditText psw;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)findViewById(R.id.user);
        psw = (EditText)findViewById(R.id.psw);
        button = (Button)findViewById(R.id.button);

        //启动的时候读取SharePerences，读取data这个文件，访问模式私有
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);      //读取数据，判断有没有点击记住密码
        boolean isRemember = preferences.getBoolean("remember",false);
        if(isRemember){

            Log.d("TAG","remember");
            //读取数据
            String u = preferences.getString("user","");
            String p = preferences.getString("psw","");

            user.setText(u);
            psw.setText(p);


        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = user.getText().toString();
                String p = psw.getText().toString();
                //创建，注意和读取的时候不同，这个是edit，两个参数分别为存储数据的文件data，访问模式私有
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                //存数据
                editor.putString("user",u);
                editor.putString("psw",p);
                editor.putBoolean("remember",true);
                //提交
                editor.commit();


            }
        });

    }
}