package com.oracle.sh.groupa.ocrdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.oracle.sh.groupa.ocrdemo.service.PeriodConnectServerService;

/**
 * Created by lliyu on 1/8/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, PeriodConnectServerService.class);
        context.startService(i);
    }
}
