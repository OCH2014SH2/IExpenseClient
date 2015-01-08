package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jishshi on 2015/1/8.
 */
public class ShowrecipeActivity extends Activity implements View.OnClickListener{
    public ListView listview;
    private ArrayAdapter adapter;
    public Spinner spinner;
    private static String[] title_list=new String[]{};
    private static String[] price_list=new String[]{};
    private static String[] date_list=new String[]{};
    private Button add;
    private Button submit;
    String title;
    String price;
    String datetime;
    static int  i=0; //初始化一次

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showrecipe);
        spinner=(Spinner)findViewById(R.id.spinner);
        listview=(ListView)findViewById(R.id.listView);
        add=(Button)findViewById(R.id.button01);
        submit=(Button)findViewById(R.id.button02);

        ArrayList<HashMap<String, String>> listItem_init = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title",title);
        map.put("price", price);
        map.put("date", datetime);
        listItem_init.add(map);
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem_init,//数据源
                R.layout.showrecipe_list_item,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"text","price","date"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.textview01,R.id.textview02,R.id.textview03}
        );
        listview.setAdapter(listItemAdapter);


        adapter = ArrayAdapter.createFromResource(this,R.array.spinner_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        title_list[i++]=title;
        price_list[i++]=price;
        date_list[i++]=datetime;
        add.setOnClickListener(this);
        submit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  }
              });





    }

    @Override
    public void onClick(View v) {

        adapter = ArrayAdapter.createFromResource(this,R.array.spinner_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        for(int j=0;j<i;j++) {
            ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title",title_list[j]);
            map.put("price", price_list[j]);
            map.put("date", date_list[j]);
            listItem.add(map);
        }

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.showrecipe_list_item,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"text","price","date"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.textview01,R.id.textview02,R.id.textview03}
        );
        //添加并且显示
        listview.setAdapter(listItemAdapter);
    }
}
