package com.oracle.sh.groupa.ocrdemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.oracle.sh.groupa.ocrdemo.ConnectionAsynTask;
import com.oracle.sh.groupa.ocrdemo.R;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jishshi on 2015/1/8.
 */
public class ShowReceiptActivity extends Activity implements View.OnClickListener {
    public ListView listview;
    private ArrayAdapter adapter;
    public Spinner spinner;
    private ArrayList<String> title_list = new ArrayList<String>();
    private ArrayList<String> price_list = new ArrayList<String>();
    private ArrayList<String> date_list = new ArrayList<String>();
    private ArrayList<LocalReceiptInfo> receiptInfos;
    private Button add;
    private Button submit;

    private LocalReceiptInfo localReceiptInfo;
    private static LocalTransaction transaction = new LocalTransaction();

    static {
        transaction.setType(LocalTransaction.TransactionType.Meals);
        LocalUser user = new LocalUser();
        user.setName("Eric Yu");
        user.setEmpId(22641);

        transaction.setApplicant(user);
        transaction.setApprover(user);
        transaction.setDateTime("2015-1-9");
        transaction.setJustification("Meal");
        transaction.setStatus(LocalTransaction.TransactionStatus.Pending);
        transaction.setLocalReceiptInfos(new ArrayList<LocalReceiptInfo>());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showreceipt);

        listview = (ListView) findViewById(R.id.list_receipts);
        add = (Button) findViewById(R.id.button_addReceipt);
        submit = (Button) findViewById(R.id.button_submitReceipts);
        // spinner = (Spinner) findViewById(R.id.spinner);

        Intent intent = getIntent();
        localReceiptInfo = (LocalReceiptInfo)intent.getSerializableExtra("data");
        transaction.addReceiptinfo(localReceiptInfo);
        receiptInfos.add(localReceiptInfo);

        ArrayList<HashMap<String, String>> listItem_init = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", localReceiptInfo.getTitle());
        map.put("price", String.valueOf(localReceiptInfo.getPrice()));
        map.put("date", localReceiptInfo.getDateTime());
        listItem_init.add(map);
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem_init,//数据源
                R.layout.showrecipe_list_item,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"text", "price", "date"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.textview01, R.id.textview02, R.id.textview03}
        );
        listview.setAdapter(listItemAdapter);


      /*  adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter); */

        title_list.add(localReceiptInfo.getTitle());
        price_list.add(String.valueOf(localReceiptInfo.getPrice()));
        date_list.add(localReceiptInfo.getDateTime());

        add.setOnClickListener(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectionAsynTask().execute(transaction);
            }
        });
    }

    @Override
    public void onClick(View v) {


       /* adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter); */
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        for (int j = 0; j < title_list.size(); j++) {

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", title_list.get(j));
            map.put("price", price_list.get(j));
            map.put("date", date_list.get(j));
            listItem.add(map);
        }

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,//数据源
                R.layout.showrecipe_list_item,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"text", "price", "date"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.textview01, R.id.textview02, R.id.textview03}
        );
        //添加并且显示
        listview.setAdapter(listItemAdapter);
    }
}
