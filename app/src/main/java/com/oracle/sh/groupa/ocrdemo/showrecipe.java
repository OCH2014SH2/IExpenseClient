package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jishshi on 2015/1/8.
 */
public class showrecipe extends Activity {
    public ListView listview;
    private ArrayAdapter adapter;
    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showrecipe);
        spinner=(Spinner)findViewById(R.id.spinner);
        listview=(ListView)findViewById(R.id.listView);

        adapter = ArrayAdapter.createFromResource(this,R.array.spinner_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       /* ArrayList<Object> listItem = new ArrayList<Object>();
        for (int j=0; j<2; j++) {
         HashMap<String, String> map=new  HashMap<String, String>()；

        }
       // listview.getCount()
        for (int j = 0; j < list.size(); j++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("text", list.get(j).getIngredient());
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.list_items,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"text"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.list_item}
        );
        //添加并且显示
        listview.setAdapter(listItemAdapter); */


    }
}
