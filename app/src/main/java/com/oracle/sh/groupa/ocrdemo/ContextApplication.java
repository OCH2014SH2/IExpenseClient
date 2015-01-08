package com.oracle.sh.groupa.ocrdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by lliyu on 1/9/2015.
 */
public class ContextApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
