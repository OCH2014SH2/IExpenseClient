package com.oracle.sh.groupa.ocrdemo.webService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.oracle.sh.groupa.ocrdemo.*;

/**
 * Created by jishshi on 2015/1/9.
 */
public class StructureActivity extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button1=(Button)findViewById(R.id.button01);
        button2=(Button)findViewById(R.id.button02);
        button3=(Button)findViewById(R.id.button03);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(StructureActivity.this,ReimburseActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(StructureActivity.this,QueryActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(StructureActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });

    }
}
