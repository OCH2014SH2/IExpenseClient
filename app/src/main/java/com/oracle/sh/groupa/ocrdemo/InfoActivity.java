package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lliyu on 1/8/2015.
 */
public class InfoActivity extends Activity{
    public ListView listview;
    private static final String[] key= new String[]{"name","contactInfo","email","managerName","costCenter","bankName","bankAccount"};
    private static final String[] value= new String[]{"Kelvin","18717872536","Kelvin.zhang@oracle.com","Chester","BL","BOC","65728945678"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        listview=(ListView)findViewById(R.id.listView);
        ArrayList<HashMap<String,String>> listItem = new ArrayList<HashMap<String,String>>();
        for (int j = 0; j < 7; j++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("key", key[j]);
            map.put("value", value[j]);
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.info_list_items,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"key","value"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.textview01,R.id.textview02}
        );
        //添加并且显示
        listview.setAdapter(listItemAdapter);


    }
}
