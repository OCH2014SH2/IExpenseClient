package com.oracle.sh.groupa.ocrdemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.oracle.sh.groupa.ocrdemo.ConnectionAsynTask;
import com.oracle.sh.groupa.ocrdemo.R;
import com.oracle.sh.groupa.ocrdemo.ReceiptAdapter;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jishshi on 2015/1/8.
 */
public class ShowReceiptActivity extends Activity implements View.OnClickListener {

    private ListView listview;
    private ArrayList<LocalReceiptInfo> receiptInfos= new ArrayList<>();
    private Button add;
    private Button submit;
    ReceiptAdapter adapter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LocalReceiptInfo localReceiptInfo = (LocalReceiptInfo)intent.getSerializableExtra("data");
        transaction.addReceiptinfo(localReceiptInfo);
        receiptInfos.add(localReceiptInfo);
        adapter.notifyDataSetChanged();
    }

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
        LocalReceiptInfo localReceiptInfo = (LocalReceiptInfo)intent.getSerializableExtra("data");
        transaction.addReceiptinfo(localReceiptInfo);
        receiptInfos.add(localReceiptInfo);


        adapter = new ReceiptAdapter(ShowReceiptActivity.this,R.layout.showreceipt_list_item,receiptInfos);
        listview.setAdapter(adapter);
        add.setOnClickListener(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectionAsynTask().execute(transaction);
                Toast.makeText(ShowReceiptActivity.this,"Receipts send successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ShowReceiptActivity.this,ReimburseActivity.class);
        startActivity(intent);
    }
}
