package com.example.obtainnetworkmessage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTools {

    public JsonTools() {
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * @param jsonString
     * @return
     */
    public static List<String> parseList(String jsonString){
        List<String> list=new ArrayList<String>();
        try {
            JSONObject jsonObject=new JSONObject(jsonString);//得到 {"citys":["北京1","北京2"..."北京23","北京24"]} 这个大对象
            JSONArray jsonArray=jsonObject.getJSONArray("citys");
            for(int i=0;i<jsonArray.length();i++){
                list.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}