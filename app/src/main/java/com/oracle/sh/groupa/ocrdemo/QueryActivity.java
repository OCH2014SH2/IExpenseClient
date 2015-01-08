package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.webService.ExpenseManager;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lliyu on 1/8/2015.
 */
public class QueryActivity extends Activity {
    String user_id = "22641";
    public Button button;
    public ListView listview;
    ExpenseManager manager;
    List<LocalTransaction> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        listview=(ListView)findViewById(R.id.listView);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //List<LocalTransaction>  list=manager.queryAllTransactStatus(user_id);
            }

        });
       /* ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for (int j = 0; j < list.size(); j++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("date", list.get(j).getDate());
            map.put("price", list.get(j).get);
            map.put("type", list.get(j).getIngredient());
            map.put("status", list.get(j).getIngredient());
            listItem.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源
                R.layout.query_list_items,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[] {"date","price","type","status"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.textview01,R.id.textview02,R.id.textview03,R.id.textview03,R.id.textview04}
        );
        //添加并且显示
        listview.setAdapter(listItemAdapter); */
    }
}