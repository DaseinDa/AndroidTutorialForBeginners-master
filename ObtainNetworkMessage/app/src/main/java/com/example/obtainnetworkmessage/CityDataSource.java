package com.example.obtainnetworkmessage;

import java.util.ArrayList;
import java.util.List;

public class CityDataSource {

    public CityDataSource() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 提供数据源
     * @return
     */
    public static List<String> getCitysList(){
        List<String> list=new ArrayList<String>();
        for(int i=0;i<25;i++){
            list.add("北京"+i);
        }//为了增强滑动效果，用for增加数据集合
        return list;
    }
}