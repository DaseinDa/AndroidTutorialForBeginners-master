package com.example.canshuchuandi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.canshuchuandi.New_Activity;
import com.example.canshuchuandi.R;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private RadioButton rb1;
    private RadioButton rb2;
    private Button bt;
    private Double height;
    private String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件
        initData();
        //实现跳转
        jump();
    }
    private void initData(){
        et=(EditText)findViewById(R.id.et);
        bt=(Button)findViewById(R.id.bt);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);

    }
    private void jump(){
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=et.getText().toString();
                //一定要注意这一步的判断，因为用户可能没有填写身高就提交了，那么这种情况下会导致程序奔溃
                if(!str.equals("")) height=Double.parseDouble(et.getText().toString());
                else{
                    et.setHint("请输入身高");
                    return;
                }
                //那么对于这种情况，我们可以在布局文件中先设置某个按钮默认的checked为true，然后根据用户来更改
                if(rb1.isChecked()){
                    sex="M";
                }else{
                    sex="F";
                }
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, New_Activity.class);
                //利用bundle来存取数据
                Bundle bundle=new Bundle();
                bundle.putDouble("height",height);
                bundle.putString("sex",sex);
                //再把bundle中的数据传给intent，以传输过去
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
            }
        });
    }
    //这里是设置获取从第二页面中返回的数据，如果我们没有设置这个的话，我们返回该页面，那么数据都会清空
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){//如果是返回的标识
            //获取数据
            Bundle bundle=data.getExtras();
            sex=bundle.getString("sex");
            height=bundle.getDouble("height");
            //保留之前的数据
            if(sex.equals("M")){
                rb1.setChecked(true);
            }else{
                rb2.setChecked(true);
            }
            String str=height.toString();
            et.setText(str);
        }
    }
}