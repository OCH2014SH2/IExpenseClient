package com.oracle.sh.groupa.ocrdemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import com.oracle.sh.groupa.ocrdemo.R;

/**
 * Created by lliyu on 1/8/2015.
 */
public class MyTabActivity extends Activity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        try {
            tabHost = (TabHost) findViewById(R.id.tabhost);
            tabHost.setup();

            //LayoutInflater.from(this).inflate(R.layout.tab, tabHost.getTabContentView(), true);
            //tabHost= (TabHost) findViewById(R.id.tabHost);
            tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Reimburse").setContent(new Intent(this, ReimburseActivity.class)));
            tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Query").setContent(new Intent(this, QueryActivity.class)));
            tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Info").setContent(new Intent(this, InfoActivity.class)));
            tabHost.setCurrentTab(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("EXCEPTION", ex.getMessage());
        }
    }
}
