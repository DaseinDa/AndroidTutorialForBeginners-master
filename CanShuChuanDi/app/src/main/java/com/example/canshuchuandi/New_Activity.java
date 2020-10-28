
package com.example.canshuchuandi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.canshuchuandi.New_Activity;
import com.example.canshuchuandi.R;

public class New_Activity extends Activity {
    private TextView textView;
    private String sex;
    private String sexText;
    private Double height;
    private String weight;
    private Button button;
    private Intent intent;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        initData();
        //设置返回上一个页面的数据
        setBackData();
    }
    private void initData(){
        textView=(TextView)findViewById(R.id.text);
        button=(Button)findViewById(R.id.button1);
        //获取上个页面传输过来的数据放在intent中
        intent=this.getIntent();
        bundle=intent.getExtras();
        sex=bundle.getString("sex");
        height=bundle.getDouble("height");
        if(sex.equals("M")){
            sexText="男性";
        }else{
            sexText="女性";
        }
        getWeight();
    }
    private void getWeight(){
        if(sex.equals("M")){
            weight=(height-80)*0.7+"";
        }else{
            weight=(height-70)*0.6+"";
        }
    }
    private void setBackData(){
        textView.setText("你是一位"+sexText+"\n你的身高是"+height+"厘米\n你的标准体重是"+weight+"公斤");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //因为我们在initData中已经将传输过来的数据放在intent中，所以这里我们直接用intent即可
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}