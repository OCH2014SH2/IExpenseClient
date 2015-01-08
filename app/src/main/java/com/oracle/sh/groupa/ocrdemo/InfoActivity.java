package com.oracle.sh.groupa.ocrdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by lliyu on 1/8/2015.
 */
public class InfoActivity extends Activity{
    public ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        listview=(ListView)findViewById(R.id.listView);

    }
}
