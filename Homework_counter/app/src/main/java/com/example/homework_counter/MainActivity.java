package com.example.homework_counter;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    Button btn_3;
    Button btn_2;
    Button btn_1;
    TextView Textshow;
    Button btn_3_B;
    Button btn_2_B;
    Button btn_1_B;
    TextView Textshow_B;
    Button btn_reset;

    protected  void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_main);
    btn_3 = (Button) findViewById(R.id.button);
    btn_2 = (Button) findViewById(R.id.button2);
    btn_1 = (Button) findViewById(R.id.button3);

    btn_3_B = (Button) findViewById(R.id.button4);
    btn_2_B = (Button) findViewById(R.id.button5);
    btn_1_B = (Button) findViewById(R.id.button6);

    btn_reset=(Button) findViewById(R.id.button7);



    Textshow = (TextView) findViewById(R.id.textView);
    //Textshow.setText(0);
        Textshow_B = (TextView) findViewById(R.id.textView2);

    btn_3.setOnClickListener(this);
    btn_2.setOnClickListener(this);
    btn_1.setOnClickListener(this);
    btn_3_B.setOnClickListener(this);
    btn_2_B.setOnClickListener(this);
    btn_1_B.setOnClickListener(this);
    btn_reset.setOnClickListener(this);




    }


public void onClick(View v){
        String exp = Textshow.getText().toString();
        String exp_B = Textshow_B.getText().toString();
    double hhh=Double.parseDouble(exp);
    double hhh_B=Double.parseDouble(exp_B);
        switch (v.getId()){
            case R.id.button:
                hhh = hhh+3;

                Textshow.setText(hhh+"");
                break;
            case R.id.button2:
                hhh = hhh+2;
                Textshow.setText(hhh+"");
                break;
            case R.id.button3:
                hhh = hhh+1;
                Textshow.setText(hhh+"");
                break;
            case R.id.button4:
                hhh_B = hhh_B+3;
                Textshow_B.setText(hhh_B+"");
                break;
            case R.id.button5:
                hhh_B = hhh_B+2;
                Textshow_B.setText(hhh_B+"");
                break;
            case R.id.button6:
                hhh_B = hhh_B+1;
                Textshow_B.setText(hhh_B+"");
                break;
            case R.id.button7:
                Textshow.setText("0");
                Textshow_B.setText("0");

        }
}



    }


