package com.oracle.sh.groupa.ocrdemo.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.oracle.sh.groupa.ocrdemo.ContextApplication;
import com.oracle.sh.groupa.ocrdemo.activities.ReimburseActivity;
import com.oracle.sh.groupa.ocrdemo.R;
import com.oracle.sh.groupa.ocrdemo.receiver.AlarmReceiver;
import com.oracle.sh.groupa.ocrdemo.webService.ExpenseManager;

/**
 * Created by lliyu on 1/8/2015.
 */
public class PeriodConnectServerService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int messageCountNeedToApprove = ExpenseManager.querySpecificNeedApprovedTransact("22641", 0);

                if (messageCountNeedToApprove > 0) {
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    Notification notification = new Notification(R.drawable.iexpense, "a iexpense message", System.currentTimeMillis());
                    Intent notificationIntent = new Intent(ContextApplication.getContext(), ReimburseActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(ContextApplication.getContext(), 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                    notification.setLatestEventInfo(ContextApplication.getContext(), "iExpense", messageCountNeedToApprove + " requests need to be handle", pi);
                    manager.notify(1, notification);
                }
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int period = 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + period;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}