package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View v){
        if(v.getId()==R.id.btn1)
            Toast.makeText(this,"button1 is clicked",Toast.LENGTH_SHORT).show();
        if(v.getId()==R.id.btn2)
            Toast.makeText(this,"button2 is clicked",Toast.LENGTH_SHORT).show();
    }
}