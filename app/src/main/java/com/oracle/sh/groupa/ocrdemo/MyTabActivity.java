package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

/**
 * Created by lliyu on 1/8/2015.
 */
public class MyTabActivity extends Activity {

    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabHost=(TabHost)findViewById(R.id.tabHost);
        //LayoutInflater.from(this).inflate(R.layout.tab, tabHost.getTabContentView(), true);
        //tabHost= (TabHost) findViewById(R.id.tabHost);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Reimburse").setContent(new Intent(this,ReimburseActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Query").setContent(new Intent(this,QueryActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Info").setContent(new Intent(this,InfoActivity.class)));
        setContentView(R.layout.tab);
    }
}
