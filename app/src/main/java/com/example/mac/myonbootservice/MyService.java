package com.example.mac.myonbootservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;


import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer;
    private NotificationManager nmgr;
    private int i ;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        nmgr=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        timer=new Timer();
        timer.schedule(new MyTask(),0,1000*10);

    }

    private class MyTask extends TimerTask{
        @Override
        public void run() {
            sendNotice();
        }
    }

    private void sendNotice(){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("xxxx");
        builder.setLights(Color.argb(111,111,111,111),200,500);
        builder.setContentTitle("標題" + i++);
        builder.setContentText("Content");
        builder.setAutoCancel(true);//訊息點了會取消

        Intent it = new Intent(this,Notical.class);
        it.putExtra("key",i);


        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(Notical.class);
        taskStackBuilder.addNextIntent(it);

        PendingIntent pendingIntent =
                taskStackBuilder.getPendingIntent
                        (0,PendingIntent.FLAG_UPDATE_CURRENT);//標題保持最新的

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        //nmgr.notify("app的唯一編號",);
        nmgr.notify(1,notification);
    }
}
