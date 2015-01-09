package com.oracle.sh.groupa.ocrdemo;

import android.os.AsyncTask;
import android.widget.Toast;

import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.webService.ExpenseManager;

/**
 * Created by lliyu on 1/9/2015.
 */
public class ConnectionAsynTask extends AsyncTask<LocalTransaction, Void, Boolean> {
    @Override
    protected Boolean doInBackground(LocalTransaction... params) {
        return ExpenseManager.submitTransaction(params[0]);

    }

    @Override
    protected void onPostExecute(Boolean b) {
        Toast.makeText(ContextApplication.getContext(),"server return value"+ b ,Toast.LENGTH_SHORT).show();
    }


}
