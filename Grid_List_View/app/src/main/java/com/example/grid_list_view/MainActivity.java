package com.example.grid_list_view;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 视图切换  列表 网格
 * @author NanFeiLong
 *
 */
public class MainActivity extends Activity implements OnClickListener,
        OnItemClickListener {

    private boolean isShowView = true;
    private GridView mGridView;
    private ListView mListView;
    private ArrayList<MyBean> mArrayList;
    private Button mBtnSelectShow;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview1);
        mGridView = (GridView) findViewById(R.id.gridview1);
        mBtnSelectShow = (Button) findViewById(R.id.btn_select_show);
        initdata();// 数据



        mBtnSelectShow.setOnClickListener(this);
        adapter = new MyAdapter(this, mArrayList);
        setLayout();


    }

    /**
     * 添加数据
     */
    private void initdata() {
        // TODO Auto-generated method stub
        mArrayList = new ArrayList<MyBean>();
        for (int i = 0; i < 20; i++) {
            mArrayList.add(new MyBean("项目"+i,3333));
        }
    }
    /**
     *实现切换视图
     */
    private void setLayout() {
        if (isShowView) {
            if (mGridView == null) {
                mGridView = (GridView) findViewById(R.id.gridview1);
            }
            mGridView.setVisibility(View.VISIBLE);
            mGridView.setAdapter(adapter);
            mGridView.setOnItemClickListener(this);
            mListView.setVisibility(View.GONE);
            mGridView.setSelection(0);
            isShowView = !isShowView;
        } else {
            if (mListView == null) {
                mListView = (ListView) findViewById(R.id.listview1);
            }
            mListView.setVisibility(View.VISIBLE);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(this);
            mGridView.setVisibility(View.GONE);
            mListView.setSelection(0);//可将第一个item对我们可见显示，用于错乱，也可以不要
            isShowView = !isShowView;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_select_show:
                setLayout();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        switch (parent.getId()) {
            case R.id.gridview1:
                Toast.makeText(MainActivity.this,
                        "gridview-" + mArrayList.get(position).getIconName(),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.listview1:
                Toast.makeText(MainActivity.this,
                        "listview-" + mArrayList.get(position).getIconName(),
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请回答");
        builder.setMessage("你是沙雕吗？？");
        builder.setPositiveButton("当然是了！！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "哈哈哈",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("你是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"那你再瞅瞅~",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("哈哈哈哈哈哈", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"???",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }

}
