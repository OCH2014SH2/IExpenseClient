package com.oracle.sh.groupa.ocrdemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oracle.sh.groupa.ocrdemo.MainActivity;
import com.oracle.sh.groupa.ocrdemo.R;

/**
 * Created by lliyu on 1/8/2015.
 */
public class LoginActivity extends Activity {

    private EditText accountText;
    private EditText passwordText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_login );

        accountText = (EditText) findViewById(R.id.Username);
        passwordText = (EditText) findViewById(R.id.Password);
        loginButton = (Button) findViewById(R.id.Login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountText.getText().toString();
                String password = passwordText.getText().toString();
                if(account.equals("22641") && password.equals("admin")){
                    Toast.makeText(LoginActivity.this,"login success",Toast.LENGTH_SHORT).show();
                    //TODO
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
